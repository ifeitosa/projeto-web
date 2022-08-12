package br.com.letscode.supernova.batatas.mapper;

import br.com.letscode.supernova.batatas.dto.ItemEstoqueInsumoConsumidoDto;
import br.com.letscode.supernova.batatas.modelos.ItemEstoqueInsumoConsumido;

public class ItemEstoqueInsumoConsumidoMapper {
    private ItemEstoqueInsumoConsumidoMapper() {
    }

    public static ItemEstoqueInsumoConsumidoDto fromEntity(ItemEstoqueInsumoConsumido entity) {
        return new ItemEstoqueInsumoConsumidoDto(entity.getId(),
                ItemEstoqueInsumoMapper.fromEntity(entity.getItemEstoqueInsumo()),
                ExecucaoFaseProcessoMapper.fromEntity(entity.getExecucaoFaseProcessamento()), entity.getDataRegistro(),
                entity.getQuantidadeProduzida(), entity.getUnidadeConsumo());
    }

    public static ItemEstoqueInsumoConsumido toEntity(ItemEstoqueInsumoConsumidoDto dto) {
        return new ItemEstoqueInsumoConsumido(dto.getId(),
                ItemEstoqueInsumoMapper.toEntity(dto.getItemEstoqueInsumo()),
                ExecucaoFaseProcessoMapper.toEntity(dto.getExecucaoFaseProcessamento()), dto.getDataRegistro(),
                dto.getQuantidadeProduzida(), dto.getUnidadeConsumo());
    }

}
