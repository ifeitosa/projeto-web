package br.com.letscode.supernova.batatas.mapper;

import java.util.stream.Collectors;

import br.com.letscode.supernova.batatas.dto.FaseDto;
import br.com.letscode.supernova.batatas.dto.InsumoConsumidoFaseDto;
import br.com.letscode.supernova.batatas.dto.InsumoDto;
import br.com.letscode.supernova.batatas.dto.ProcessoDto;
import br.com.letscode.supernova.batatas.modelos.Fase;
import br.com.letscode.supernova.batatas.modelos.Insumo;
import br.com.letscode.supernova.batatas.modelos.InsumoConsumidoFase;
import br.com.letscode.supernova.batatas.modelos.Processo;

public class ProcessoMapper {

    public static ProcessoDto fromEntity(Processo p) {
        return new ProcessoDto(p.getNome(), p.getDescricao(), p.getDataRegistro(), p.getResponsavel(), p.getFases().stream().map(ProcessoMapper::fromEntity).collect(Collectors.toList()));
    }

    public static FaseDto fromEntity(Fase f) {
        return new FaseDto(f.getSequencia(), f.getNome(), f.getInstrucoes(), f.getUnidadeProducao(), f.getQuantidadeProduzida(),
            f.getInsumosConsumidos().stream().map(ProcessoMapper::fromEntity).collect(Collectors.toList()));
    }

    
    public static InsumoDto fromEntity(Insumo in) {
        return new InsumoDto(in.getId(), in.getNome(), in.getUnidadeMedida(), in.getEstoqueMinimo());
    }

    public static InsumoConsumidoFaseDto fromEntity(InsumoConsumidoFase ic) {
        return new InsumoConsumidoFaseDto(ic.getId(), ProcessoMapper.fromEntity(ic.getInsumo()), ic.getQuantidadeConsumida(),
            ic.getUnidadeConsumo());
    }
    
}
