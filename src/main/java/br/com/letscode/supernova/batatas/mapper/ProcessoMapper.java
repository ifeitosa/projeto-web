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
        return new ProcessoDto(p.getId(), p.getNome(), p.getDescricao(), p.getDataRegistro(), p.getResponsavel(), p.getFases().stream().map(ProcessoMapper::fromEntity).collect(Collectors.toList()));
    }

    public static FaseDto fromEntity(Fase f) {
        return new FaseDto(f.getSequencia(), f.getNome(), f.getInstrucoes(), f.getUnidadeProducao(), f.getQuantidadeProduzida(),
            f.getInsumosConsumidos().stream().map(ProcessoMapper::fromEntity).collect(Collectors.toList()));
    }

    
    public static InsumoDto fromEntity(Insumo in) {
        return new InsumoDto(in.getNome(), in.getUnidadeMedida(), in.getEstoqueMinimo());
    }

    public static InsumoConsumidoFaseDto fromEntity(InsumoConsumidoFase ic) {
        return new InsumoConsumidoFaseDto(ProcessoMapper.fromEntity(ic.getInsumo()), ic.getQuantidadeConsumida(),
            ic.getUnidadeConsumo());
    }

    public static Processo toEntity(ProcessoDto dto) {
        Processo processo = new Processo(null, dto.getNome(), dto.getDescricao(), dto.getDataRegistro(), dto.getResponsavel(), null);
        processo.setFases(dto.getFases().stream().map(fdto -> ProcessoMapper.toEntity(fdto, processo)).collect(Collectors.toList()));
	return processo;
    }

    public static Fase toEntity(FaseDto dto, Processo processo) {
        Fase fase = new Fase(null, processo, dto.getSequencia(), dto.getNome(), 
            dto.getInstrucoes(), dto.getUnidadeProducao(), dto.getQuantidadeProduzida(), 
            dto.getInsumosConsumidos().stream().map(ProcessoMapper::toEntity)
                .collect(Collectors.toList()));
	return fase;
    }

    public static InsumoConsumidoFase toEntity(InsumoConsumidoFaseDto dto) {
        return new InsumoConsumidoFase(null, ProcessoMapper.toEntity(dto.getInsumo()), dto.getQuantidadeConsumida(), dto.getUnidadeConsumo());
    }

    public static Insumo toEntity(InsumoDto dto) {
        return new Insumo(null, dto.getNome(), dto.getUnidadeMedida(), dto.getEstoqueMinimo());
    }
    
}
