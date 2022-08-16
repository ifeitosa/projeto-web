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
import br.com.letscode.supernova.batatas.modelos.Processo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioFase;
import br.com.letscode.supernova.batatas.repositorios.RepositorioInsumo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioInsumoConsumidoFase;
import br.com.letscode.supernova.batatas.repositorios.RepositorioInsumoProduzidoFase;
import br.com.letscode.supernova.batatas.repositorios.RepositorioProcesso;

@Service
public class ProcessoService {

    @Autowired
    private RepositorioProcesso repositorioProcesso;
    @Autowired
    private RepositorioFase repositorioFase;
    @Autowired
    private RepositorioInsumoConsumidoFase repositorioInsumoConsumido;
    @Autowired
    private RepositorioInsumoProduzidoFase repositorioInsumoProduzido;
    @Autowired
    private RepositorioInsumo repositorioInsumo;

    @Transactional
    public ProcessoDto adicionarProcesso(ProcessoDto dto) {
        Processo processo = ProcessoMapper.toEntity(dto);
        processo.setId(null);
        processo.setFases(processo.getFases().stream().map(this.repositorioFase::save).map(f -> {
            f.setInsumosConsumidos(f.getInsumosConsumidos().stream().map(ic -> {
                ic.setFase(f);
                ic.setInsumo(this.repositorioInsumo.save(ic.getInsumo()));
                return this.repositorioInsumoConsumido.save(ic);
            }).collect(Collectors.toList()));
            f.setInsumosProduzidos(f.getInsumosProduzidos().stream().map(ic -> {
                ic.setFase(f);
                ic.setInsumo(this.repositorioInsumo.save(ic.getInsumo()));
                return this.repositorioInsumoProduzido.save(ic);
            }).collect(Collectors.toList()));
            return this.repositorioFase.save(f);
        }).collect(Collectors.toList()));
        return ProcessoMapper.fromEntity(this.repositorioProcesso.save(processo));
    }

    @Transactional
    public ProcessoDto corrigirProcesso(Long id, ProcessoDto dto) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(dto);

        Optional<Processo> processo = this.repositorioProcesso.findById(id);        
        if (processo.isPresent()) {
            return processo.map(p -> {
                p.setId(id);
                p.setNome(dto.getNome());
                p.setDataRegistro(dto.getDataRegistro());
                p.setDescricao(dto.getDescricao());
                p.setResponsavel(dto.getResponsavel());
                p.setFases(p.getFases().stream().map(this.repositorioFase::save).map(f -> {
                    f.setInsumosConsumidos(f.getInsumosConsumidos().stream().map(ic -> {
                        ic.setInsumo(this.repositorioInsumo.save(ic.getInsumo()));
                        return this.repositorioInsumoConsumido.save(ic);
                    }).collect(Collectors.toList()));
                    f.setInsumosProduzidos(f.getInsumosProduzidos().stream().map(ic -> {
                        ic.setInsumo((this.repositorioInsumo.save(ic.getInsumo())));
                        return this.repositorioInsumoProduzido.save(ic);
                    }).collect(Collectors.toList()));
                    return this.repositorioFase.save(f);
                }).collect(Collectors.toList()));
                
                return ProcessoMapper.fromEntity(this.repositorioProcesso.save(p));
            }).get();
        }
        return null;

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

    public void deletarProcesso(Long id) {
        this.repositorioProcesso.deleteById(id);
        Optional<Processo> processo = this.repositorioProcesso.findById(id);
        processo.ifPresent(p -> {
            p.getFases().stream().forEach(f -> {
                f.getInsumosProduzidos().stream().forEach(ip -> {
                    repositorioInsumo.delete(ip.getInsumo());
                    repositorioInsumoProduzido.delete(ip);
                });
                f.getInsumosConsumidos().stream().forEach(ic -> {
                    repositorioInsumo.delete(ic.getInsumo());
                    repositorioInsumoConsumido.delete(ic);
                });
                this.repositorioFase.delete(f);
            });
            this.repositorioProcesso.delete(p);
        });
    }
}
