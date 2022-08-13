package br.com.letscode.supernova.batatas.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.letscode.supernova.batatas.dto.ItemEstoqueInsumoConsumidoDto;
import br.com.letscode.supernova.batatas.modelos.ItemEstoqueInsumoConsumido;

@Component
public class ItemEstoqueInsumoConsumidoMapper {

    @Autowired ExecucaoFaseProcessoMapper mapper;
    

    public ItemEstoqueInsumoConsumidoDto fromEntity(ItemEstoqueInsumoConsumido entity) {
        return new ItemEstoqueInsumoConsumidoDto(entity.getId(),
                ItemEstoqueInsumoMapper.fromEntity(entity.getItemEstoqueInsumo()),
                this.mapper.fromEntity(entity.getExecucaoFaseProcessamento()), entity.getDataRegistro(),
                entity.getQuantidadeProduzida(), entity.getUnidadeConsumo());
    }

    public ItemEstoqueInsumoConsumido toEntity(ItemEstoqueInsumoConsumidoDto dto) {
        return new ItemEstoqueInsumoConsumido(dto.getId(),
                ItemEstoqueInsumoMapper.toEntity(dto.getItemEstoqueInsumo()),
                this.mapper.toEntity(dto.getExecucaoFaseProcessamento()), dto.getDataRegistro(),
                dto.getQuantidadeProduzida(), dto.getUnidadeConsumo());
    }

}
