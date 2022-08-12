package br.com.letscode.supernova.batatas.mapper;

import br.com.letscode.supernova.batatas.dto.ItemEstoqueInsumoDto;
import br.com.letscode.supernova.batatas.modelos.ItemEstoqueInsumo;

public class ItemEstoqueInsumoMapper {

    private ItemEstoqueInsumoMapper() {

    }
    
    public static ItemEstoqueInsumoDto fromEntity(ItemEstoqueInsumo item) {
        return new ItemEstoqueInsumoDto(item.getLote(), InsumoMapper.fromEntity(item.getInsumo()), item.getQuantidade(), item.getDataAquisicao(), item.getDataValidade(), item.getQualidade());
    }

    public static ItemEstoqueInsumo toEntity(ItemEstoqueInsumoDto dto) {
        return new ItemEstoqueInsumo(dto.getLote(), InsumoMapper.toEntity(dto.getInsumo()), dto.getQuantidade(), dto.getDataAquisicao(), dto.getDataValidade(), dto.getQualidade());
    }

}
