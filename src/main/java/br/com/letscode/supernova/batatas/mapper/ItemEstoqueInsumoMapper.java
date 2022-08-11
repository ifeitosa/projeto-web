package br.com.letscode.supernova.batatas.mapper;

import br.com.letscode.supernova.batatas.dto.InsumoDto;
import br.com.letscode.supernova.batatas.dto.ItemEstoqueInsumoDto;
import br.com.letscode.supernova.batatas.modelos.Insumo;
import br.com.letscode.supernova.batatas.modelos.ItemEstoqueInsumo;

public class ItemEstoqueInsumoMapper {

    private ItemEstoqueInsumoMapper() {

    }
    
    public static ItemEstoqueInsumoDto fromEntity(ItemEstoqueInsumo item) {
        return new ItemEstoqueInsumoDto(item.getLote(), fromEntity(item.getInsumo()), item.getQuantidade(), item.getDataAquisicao(), item.getDataValidade(), item.getQualidade());
    }

    public static InsumoDto fromEntity(Insumo insumo) {
        return new InsumoDto(insumo.getId(), insumo.getNome(), insumo.getUnidadeMedida(), insumo.getEstoqueMinimo());
    }
}
