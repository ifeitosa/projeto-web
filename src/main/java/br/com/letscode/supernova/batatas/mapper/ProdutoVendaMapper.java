package br.com.letscode.supernova.batatas.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.letscode.supernova.batatas.dto.IdProcesso;
import br.com.letscode.supernova.batatas.dto.ProdutoVendaDto;
import br.com.letscode.supernova.batatas.modelos.ProdutoVenda;
import br.com.letscode.supernova.batatas.repositorios.RepositorioProcesso;

@Component
public class ProdutoVendaMapper {
    @Autowired
    private RepositorioProcesso repositorioProcesso;

    public ProdutoVenda toEntity(ProdutoVendaDto dto) {
        ProdutoVenda pv = new ProdutoVenda(null, repositorioProcesso.getReferenceById(dto.getProcesso().getId()),
                dto.getDescricao(), dto.getUnidadeMedida(), dto.getQuantidade(), dto.getCustoProducao());
        return pv;
    }

    public ProdutoVendaDto fromEntity(ProdutoVenda entity) {
        return new ProdutoVendaDto(entity.getId(), new IdProcesso(entity.getProcesso().getId()), entity.getDescricao(),
                entity.getUnidadeMedida(), entity.getQuantidade(), entity.getCustoProducao());
    }
}
