package br.com.letscode.supernova.batatas.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.letscode.supernova.batatas.dto.ExecucaoFaseProcessamentoDto;
import br.com.letscode.supernova.batatas.dto.IdFaseDto;
import br.com.letscode.supernova.batatas.modelos.ExecucaoFaseProcessamento;
import br.com.letscode.supernova.batatas.modelos.Fase;

import br.com.letscode.supernova.batatas.repositorios.RepositorioFase;

@Component
public class ExecucaoFaseProcessoMapper {
    
    @Autowired
    private RepositorioFase repositorioFase;

    private ExecucaoFaseProcessoMapper() {
    }

    public ExecucaoFaseProcessamentoDto fromEntity(ExecucaoFaseProcessamento entity) {
        return new ExecucaoFaseProcessamentoDto(entity.getOS(), this.fromEntity(entity.getFase()),
                entity.getDataInicio(), entity.getDataTermino());
    }

    private IdFaseDto fromEntity(Fase fase) {
        return new IdFaseDto(fase.getId());
    }

    public ExecucaoFaseProcessamento toEntity(ExecucaoFaseProcessamentoDto execucao) {
        return new ExecucaoFaseProcessamento(execucao.getOS(), this.toEntity(execucao.getFase()),
                execucao.getDataInicio(), execucao.getDataTermino());
    }

    private Fase toEntity(IdFaseDto fase) {
        return this.repositorioFase.findById(fase.getId()).get();
    }
}
