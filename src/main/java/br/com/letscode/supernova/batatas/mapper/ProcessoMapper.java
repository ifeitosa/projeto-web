package br.com.letscode.supernova.batatas.mapper;

import java.util.stream.Collectors;

import br.com.letscode.supernova.batatas.dto.FaseDto;
import br.com.letscode.supernova.batatas.dto.InsumoConsumidoFaseDto;
import br.com.letscode.supernova.batatas.dto.InsumoProduzidoFaseDto;
import br.com.letscode.supernova.batatas.dto.ProcessoDto;
import br.com.letscode.supernova.batatas.modelos.Fase;
import br.com.letscode.supernova.batatas.modelos.InsumoConsumidoFase;
import br.com.letscode.supernova.batatas.modelos.InsumoProduzidoFase;
import br.com.letscode.supernova.batatas.modelos.Processo;

public class ProcessoMapper {

    private ProcessoMapper() {

    }

    public static ProcessoDto fromEntity(Processo p) {
        return new ProcessoDto(p.getId(), p.getNome(), p.getDescricao(), p.getDataRegistro(), p.getResponsavel(),
                p.getFases().stream().map(ProcessoMapper::fromEntity).collect(Collectors.toList()));
    }

    public static FaseDto fromEntity(Fase f) {
        return new FaseDto(f.getSequencia(), f.getNome(), f.getInstrucoes(), f.getUnidadeProducao(),
                f.getQuantidadeProduzida(),
                f.getInsumosConsumidos().stream().map(ProcessoMapper::fromEntity)
                        .collect(Collectors.toList()),
                f.getInsumosProduzidos().stream().map(ProcessoMapper::fromEntity).collect(Collectors.toList()));
    }


    public static InsumoConsumidoFaseDto fromEntity(InsumoConsumidoFase ic) {
        return new InsumoConsumidoFaseDto(InsumoMapper.fromEntity(ic.getInsumo()), ic.getQuantidadeConsumida(),
                ic.getUnidadeConsumo());
    }

    public static InsumoProduzidoFaseDto fromEntity(InsumoProduzidoFase ic) {
        return new InsumoProduzidoFaseDto(InsumoMapper.fromEntity(ic.getInsumo()), ic.getQuantidadeProduzida(), ic.getUnidadeProducao());
    }

    public static Processo toEntity(ProcessoDto dto) {
        return new Processo(null, dto.getNome(), dto.getDescricao(), dto.getDataRegistro(), dto.getResponsavel(),
                dto.getFases().stream().map(ProcessoMapper::toEntity).collect(Collectors.toList()));
    }

    public static Fase toEntity(FaseDto dto) {
        Fase fase = new Fase(null, dto.getSequencia(), dto.getNome(),
                dto.getInstrucoes(), dto.getUnidadeProducao(), dto.getQuantidadeProduzida(),
                null, null);
        fase.setInsumosConsumidos(dto.getInsumosConsumidos().stream().map(ic -> ProcessoMapper.toEntity(ic, fase))
                .collect(Collectors.toList()));
        fase.setInsumosProduzidos(dto.getInsumoProduzidos().stream().map(ic -> ProcessoMapper.toEntity(ic, fase)).collect(Collectors.toList()));
        return fase;
    }

    private static InsumoProduzidoFase toEntity(InsumoProduzidoFaseDto dto, Fase fase) {
        return new InsumoProduzidoFase(null, fase, InsumoMapper.toEntity(dto.getInsumo()), dto.getQuantidadeProduzida(), dto.getUnidadeProducao());
    }

    public static InsumoConsumidoFase toEntity(InsumoConsumidoFaseDto dto, Fase fase) {
        return new InsumoConsumidoFase(null, fase, InsumoMapper.toEntity(dto.getInsumo()), dto.getQuantidadeConsumida(),
                dto.getUnidadeConsumo());
    }
}
