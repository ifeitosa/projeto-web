package br.com.letscode.supernova.batatas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.letscode.supernova.batatas.dto.InsumoDto;
import br.com.letscode.supernova.batatas.mapper.InsumoMapper;
import br.com.letscode.supernova.batatas.modelos.Insumo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioInsumo;

@Service
public class InsumoService {

    @Autowired
    private RepositorioInsumo repositorio;

    public Optional<InsumoDto> obterInsumo(Long id) {
        return this.repositorio.findById(id).map(InsumoMapper::fromEntity);
    }

    public List<InsumoDto> obterInsumos() {
        return this.repositorio.findAll().stream().map(InsumoMapper::fromEntity).collect(Collectors.toList());
    }

    public Optional<InsumoDto> corrigirInsumo(Long id, InsumoDto dto) {
        Optional<Insumo> talvezInsumo = this.repositorio.findById(id);
        if (talvezInsumo.isPresent()) {
            Insumo insumo = talvezInsumo.get();
            insumo.setEstoqueMinimo(dto.getEstoqueMinimo());
            insumo.setNome(dto.getNome());
            insumo.setUnidadeMedida(dto.getUnidadeMedida());
            this.repositorio.save(insumo);
            return Optional.of(InsumoMapper.fromEntity(insumo));
        } else {
            return Optional.ofNullable(null);
        }
    }

    public Optional<InsumoDto> inserir(InsumoDto dto) {
        Insumo insumo = InsumoMapper.toEntity(dto);
        insumo = this.repositorio.save(insumo);
        return Optional.of(InsumoMapper.fromEntity(insumo));
    }
    
}
