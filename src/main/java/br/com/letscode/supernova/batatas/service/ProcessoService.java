package br.com.letscode.supernova.batatas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.letscode.supernova.batatas.dto.FaseDto;
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
    private EntityManager em;

    public ProcessoService(@Autowired RepositorioProcesso repositorioProcesso,
            @Autowired RepositorioFase repositorioFase,
            @Autowired RepositorioInsumoConsumidoFase repositorioInsumoConsumidoFase,
            @Autowired RepositorioInsumo repositorioInsumo, @Autowired EntityManager em) {
        this.repositorioProcesso = repositorioProcesso;
        this.repositorioFase = repositorioFase;
        this.repositorioInsumoConsumidoFase = repositorioInsumoConsumidoFase;
        this.repositorioInsumo = repositorioInsumo;
        this.em = em;
    }

    @Transactional
    public ProcessoDto adicionarProcesso(ProcessoDto dto) {
        Processo processo = ProcessoMapper.toEntity(dto);
        processo.setId(null);
        processo = this.repositorioProcesso.save(processo);
        processo.setFases(processo.getFases().stream().map(this.repositorioFase::save).peek(f -> {
            f.setInsumosConsumidos(f.getInsumosConsumidos().map(ic -> {
                ic.setFase(f);
                ic.setInsumo(this.repositorioInsumo.save(ic.getInsumo()));
                return this.repositorioInsumoConsumidoFase.save(ic);
            }).collect(Collectors.toUnmodifiableList()));
        }).collect(Collectors.toUnmodifiableList()));
        processo.getFases().stream().forEach(this.repositorioFase::save);
        return ProcessoMapper.fromEntity(this.repositorioProcesso.saveAndFlush(processo));
        /*
         * processo.setFases(processo.getFases().stream().map(f -> {
         * Fase g = this.repositorioFase.save(f);
         * g.setInsumosConsumidos(g.getInsumosConsumidos().stream()
         * .map((Function<? super InsumoConsumidoFase, ? extends InsumoConsumidoFase>)
         * ic -> {
         * ic.setFase(g);
         * ic.setInsumo(this.repositorioInsumo.save(ic.getInsumo()));
         * return this.repositorioInsumoConsumidoFase.save(ic);
         * }).collect(Collectors.toUnmodifiableList()));
         * return g;
         * }).collect(Collectors.toUnmodifiableList()));
         */
    }

    @Transactional
    public Optional<ProcessoDto> corrigirProcesso(Long id, ProcessoDto dto) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(dto);

        Optional<Processo> processo = this.repositorioProcesso.findById(id);
        if (!processo.isPresent())
            return null;
        this.repositorioProcesso.deleteById(id);
        return Optional.ofNullable(processo.map(p -> {
            p.setId(id);
            p.setNome(dto.getNome());
            p.setDataRegistro(dto.getDataRegistro());
            p.setDescricao(dto.getDescricao());
            p.setResponsavel(dto.getResponsavel());
            p.setFases(dto.getFases().stream().map(ProcessoMapper::toEntity)
                    .collect(Collectors.toUnmodifiableList()));
            return ProcessoMapper.fromEntity(this.repositorioProcesso.save(p));
        }).get());
        /*
         * Processo processo = talvezProcesso.get();
         * dto.setId(processo.getId());
         * 
         * final Processo p = em.merge(this.em.merge(ProcessoMapper.toEntity(dto)));
         * p.getFases().stream().forEach(f -> {
         * f.setProcesso(p);
         * Fase g = this.em.merge(f);
         * g.setInsumosConsumidos(g.getInsumosConsumidos().stream().map(ic -> {
         * ic.setInsumo(em.merge(ic.getInsumo()));
         * ic.setFase(g);
         * return em.merge(ic);
         * }).collect(Collectors.toList()));
         * });
         * em.merge(p);
         * em.flush();
         * return ProcessoMapper.fromEntity(p);
         */
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
