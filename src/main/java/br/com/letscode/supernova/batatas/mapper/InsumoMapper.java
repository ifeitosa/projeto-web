package br.com.letscode.supernova.batatas.mapper;

import br.com.letscode.supernova.batatas.dto.InsumoDto;
import br.com.letscode.supernova.batatas.modelos.Insumo;

public class InsumoMapper {
    private InsumoMapper() {

    }

    public static Insumo toEntity(InsumoDto dto) {
        return new Insumo(null, dto.getNome(), dto.getUnidadeMedida(), dto.getEstoqueMinimo());
    }
    
    public static InsumoDto fromEntity(Insumo insumo) {
        return new InsumoDto(insumo.getId(), insumo.getNome(), insumo.getUnidadeMedida(), insumo.getEstoqueMinimo());
    }
}
