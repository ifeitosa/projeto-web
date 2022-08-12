package br.com.letscode.supernova.batatas.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.letscode.supernova.batatas.dto.ItemEstoqueInsumoDto;
import br.com.letscode.supernova.batatas.exceptions.InsumoNaoEncontradoException;
import br.com.letscode.supernova.batatas.mapper.ItemEstoqueInsumoMapper;
import br.com.letscode.supernova.batatas.modelos.Insumo;
import br.com.letscode.supernova.batatas.modelos.ItemEstoqueInsumo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioInsumo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioItemEstoqueInsumo;

@Service
public class ItemEstoqueInsumoService {
    @Autowired
    private RepositorioItemEstoqueInsumo repositorioItens;
    @Autowired
    private RepositorioInsumo repositorioInsumo;

    public List<ItemEstoqueInsumoDto> obterItensDoEstoqueAtivos() {
        final LocalDate agora = LocalDate.now();
        return this.repositorioItens.findByQuantidadeIsGreaterThanAndDataValidadeIsGreaterThanEqual(0.0D, agora).stream()
            .map(ItemEstoqueInsumoMapper::fromEntity).collect(Collectors.toList());
    }

    public List<ItemEstoqueInsumoDto> obterItensDoEstoqueVencidos() {
        final LocalDate agora = LocalDate.now();
        return this.repositorioItens.encontrarItensComDataValidadeVencida(agora).stream()
            .map(ItemEstoqueInsumoMapper::fromEntity).collect(Collectors.toList());
    }

    public Optional<ItemEstoqueInsumoDto> obterItemDoEstoque(Long id) {
        return this.repositorioItens.findById(id).map(ItemEstoqueInsumoMapper::fromEntity);
    }

    public List<ItemEstoqueInsumoDto> obterItensDoEstoque() {
        return this.repositorioItens.findByQuantidadeIsGreaterThan(0.0D).stream()
            .map(ItemEstoqueInsumoMapper::fromEntity).collect(Collectors.toList());
    }

    public List<ItemEstoqueInsumoDto> obterItensSemEstoqueMinimo() {
        final LocalDate agora = LocalDate.now();
        return this.repositorioItens.encontrarItensSemEstoqueMinimo(agora).stream()
            .map(ItemEstoqueInsumoMapper::fromEntity).collect(Collectors.toList());
    }

    public Optional<ItemEstoqueInsumoDto> corrigirItemEstoqueInsumo(Long id, ItemEstoqueInsumoDto dto) {
        Optional<ItemEstoqueInsumo> item = this.repositorioItens.findById(id);
        return item.map(i -> {
            i.setDataAquisicao(dto.getDataAquisicao());
            i.setDataValidade(dto.getDataValidade());
            i.setQualidade(dto.getQualidade());
            i.setQuantidade(dto.getQuantidade());
            return this.repositorioItens.save(i);
        }).map(ItemEstoqueInsumoMapper::fromEntity);
    }

    public Optional<ItemEstoqueInsumoDto> inserirItemEstoqueInsumo(ItemEstoqueInsumoDto dto) {
        // Verifica se o insumo passado corresponde a um Insumo do banco de dados
        Optional<Insumo> talvezInsumo = this.repositorioInsumo.findById(dto.getInsumo().getId());
        return talvezInsumo.map(insumo -> {
            if (!insumo.getId().equals(dto.getInsumo().getId())) {
                throw new InsumoNaoEncontradoException("Insumo referido inexistente.");
            }
            return insumo;
        }).map(insumo -> {
            ItemEstoqueInsumo item = ItemEstoqueInsumoMapper.toEntity(dto);
            item.setLote(null);
            item.setInsumo(insumo);
            return this.repositorioItens.save(item);
        }).map(ItemEstoqueInsumoMapper::fromEntity);
    }


}
