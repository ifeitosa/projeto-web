package br.com.letscode.supernova.batatas.mapper;

import br.com.letscode.supernova.batatas.dto.ExecucaoFaseProcessamentoDto;
import br.com.letscode.supernova.batatas.modelos.ExecucaoFaseProcessamento;

public class ExecucaoFaseProcessoMapper {
    private ExecucaoFaseProcessoMapper() {
    }

    public static ExecucaoFaseProcessamentoDto fromEntity(ExecucaoFaseProcessamento entity) {
        return new ExecucaoFaseProcessamentoDto(entity.getOS(), ProcessoMapper.fromEntity(entity.getFase()),
                entity.getDataInicio(), entity.getDataTermino());
    }

    public static ExecucaoFaseProcessamento toEntity(ExecucaoFaseProcessamentoDto execucao) {
        return new ExecucaoFaseProcessamento(execucao.getOS(), ProcessoMapper.toEntity(execucao.getFase()),
                execucao.getDataInicio(), execucao.getDataTermino());
    }
}
