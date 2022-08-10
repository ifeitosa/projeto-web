package br.com.letscode.supernova.batatas.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.letscode.supernova.batatas.dto.ProcessoDto;
import br.com.letscode.supernova.batatas.mapper.ProcessoMapper;
import br.com.letscode.supernova.batatas.modelos.Fase;
import br.com.letscode.supernova.batatas.modelos.InsumoConsumidoFase;
import br.com.letscode.supernova.batatas.modelos.Processo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioFase;
import br.com.letscode.supernova.batatas.repositorios.RepositorioInsumo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioInsumoConsumidoFase;
import br.com.letscode.supernova.batatas.repositorios.RepositorioProcesso;

@Service
public class ProcessoService {
    
    private RepositorioProcesso repositorioProcesso;
    private RepositorioFase repositorioFase;
    private RepositorioInsumoConsumidoFase repositorioInsumoConsumidoFase;
    private RepositorioInsumo repositorioInsumo;

    public ProcessoService(@Autowired RepositorioProcesso repositorioProcesso,
    @Autowired RepositorioFase repositorioFase, @Autowired RepositorioInsumoConsumidoFase repositorioInsumoConsumidoFase,
    @Autowired RepositorioInsumo repositorioInsumo) {
        this.repositorioProcesso = repositorioProcesso;
        this.repositorioFase = repositorioFase;
        this.repositorioInsumoConsumidoFase = repositorioInsumoConsumidoFase;
        this.repositorioInsumo = repositorioInsumo;
    }

    @Transactional
    public ProcessoDto adicionarProcesso(ProcessoDto dto) {
        Processo processo = ProcessoMapper.toEntity(dto);
        return ProcessoMapper.fromEntity(this.repositorioProcesso.save(processo));
    }

    @Transactional
    public ProcessoDto corrigirProcesso(Long id, ProcessoDto dto) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(dto);

        Optional<Processo> talvezProcesso = this.repositorioProcesso.findById(id);
        if (talvezProcesso.isEmpty()) return null;
        Processo processo = ProcessoMapper.toEntity(dto);
        processo.setId(id);
        processo = this.repositorioProcesso.saveAndFlush(processo);
        return ProcessoMapper.fromEntity(processo);
    }

    public Optional<ProcessoDto> obterProcesso(Long id) {
        Objects.requireNonNull(id);
        Optional<Processo> processo = this.repositorioProcesso.findById(id);
        return processo.map(ProcessoMapper::fromEntity);
    }

    public List<ProcessoDto> obterProcessos() {
        return this.repositorioProcesso.findAll()
                .stream()
                .map(ProcessoMapper::fromEntity)
                .collect(Collectors.toList());
    }
}
