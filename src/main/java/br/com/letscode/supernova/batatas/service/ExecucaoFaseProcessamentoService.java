package br.com.letscode.supernova.batatas.service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.letscode.supernova.batatas.dto.ExecucaoFaseProcessamentoDto;
import br.com.letscode.supernova.batatas.mapper.ExecucaoFaseProcessamentoMapper;
import br.com.letscode.supernova.batatas.modelos.ExecucaoFaseProcessamento;
import br.com.letscode.supernova.batatas.repositorios.RepositorioExecucaoFaseProcessamento;

@Service
public class ExecucaoFaseProcessamentoService {
    @Autowired
    ExecucaoFaseProcessamentoMapper mapper;
    @Autowired
    RepositorioExecucaoFaseProcessamento repositorioExecucaoFaseProcessamento;

    public ExecucaoFaseProcessamentoDto encontrarPeloId(Long id) {
        try {
            return this.repositorioExecucaoFaseProcessamento.findById(id).map(mapper::fromEntity).get();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Execução de fase de processamento não encontrada.");
        }
    }

    public List<ExecucaoFaseProcessamentoDto> encontrarPelaFase(Long idFase) {
        return this.repositorioExecucaoFaseProcessamento.findByIdFase(idFase).stream().map(mapper::fromEntity)
                .collect(Collectors.toList());
    }

    public List<ExecucaoFaseProcessamentoDto> encontrarPelaDataEntre(LocalDate inicio, LocalDate termino) {
        if (inicio == null) inicio = LocalDate.now().minusDays(30);
        if (termino == null) termino = LocalDate.now();
        return this.repositorioExecucaoFaseProcessamento.encontrarTodosQueIniciaramOuTerminaramEntre(inicio, termino)
                .stream()
                .map(mapper::fromEntity).collect(Collectors.toList());
    }

    public ExecucaoFaseProcessamentoDto inserirExecucaoFaseProcessamento(ExecucaoFaseProcessamentoDto dto) {
        ExecucaoFaseProcessamento execucaoFaseProcessamento = mapper.toEntity(dto);
        execucaoFaseProcessamento = this.repositorioExecucaoFaseProcessamento.save(execucaoFaseProcessamento);
        return mapper.fromEntity(execucaoFaseProcessamento);
    }

    public ExecucaoFaseProcessamentoDto alterarExecucaoFaseProcessamento(ExecucaoFaseProcessamentoDto dto) {
        ExecucaoFaseProcessamento execucaoFaseProcessamento = this.repositorioExecucaoFaseProcessamento
                .getReferenceById(dto.getOS());
        execucaoFaseProcessamento.setDataInicio(dto.getDataInicio());
        execucaoFaseProcessamento.setDataTermino(dto.getDataTermino());
        return mapper.fromEntity(this.repositorioExecucaoFaseProcessamento.save(execucaoFaseProcessamento));
    }

}
