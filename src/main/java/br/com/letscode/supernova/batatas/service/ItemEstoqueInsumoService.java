package br.com.letscode.supernova.batatas.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.letscode.supernova.batatas.dto.ItemEstoqueInsumoDto;
import br.com.letscode.supernova.batatas.mapper.ItemEstoqueInsumoMapper;
import br.com.letscode.supernova.batatas.repositorios.RepositorioInsumo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioItemEstoqueInsumo;

@Service
public class ItemEstoqueInsumoService {
    @Autowired
    private RepositorioItemEstoqueInsumo repositorioItens;
    @Autowired
    private RepositorioInsumo repositorioInsumo;


    public List<ItemEstoqueInsumoDto> obterItensDoEstoque() {
        final ZonedDateTime agora = ZonedDateTime.now();
        return this.repositorioItens.findByQuantidadeIsGreaterThan(0.0D).stream()
        .filter(item -> item.getDataValidade().isAfter(agora))
            .map(ItemEstoqueInsumoMapper::fromEntity).collect(Collectors.toList());
    }


}
