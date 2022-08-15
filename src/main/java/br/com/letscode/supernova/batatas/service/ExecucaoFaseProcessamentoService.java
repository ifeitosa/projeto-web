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
import br.com.letscode.supernova.batatas.modelos.ItemEstoqueInsumo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioExecucaoFaseProcessamento;
import br.com.letscode.supernova.batatas.repositorios.RepositorioInsumo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioItemEstoqueInsumo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioItemEstoqueInsumoConsumido;
import br.com.letscode.supernova.batatas.repositorios.RepositorioItemEstoqueInsumoProduzido;
import br.com.letscode.supernova.batatas.repositorios.RepositorioItemProduzidoExecucao;
import br.com.letscode.supernova.batatas.repositorios.RepositorioLoteProdutoVenda;
import br.com.letscode.supernova.batatas.repositorios.RepositorioProdutoVenda;

@Service
public class ExecucaoFaseProcessamentoService {
    @Autowired
    ExecucaoFaseProcessamentoMapper mapper;
    @Autowired
    RepositorioExecucaoFaseProcessamento repositorioExecucaoFaseProcessamento;
    @Autowired
    RepositorioInsumo repositorioInsumo;
    @Autowired
    RepositorioItemEstoqueInsumoConsumido repositorioItemEstoqueInsumoConsumido;
    @Autowired
    RepositorioItemEstoqueInsumoProduzido repositorioItemEstoqueInsumoProduzido;
    @Autowired
    RepositorioItemEstoqueInsumo repositorioItemEstoqueInsumo;
    @Autowired
    RepositorioItemProduzidoExecucao repositorioItemProduzidoExecucao;
    @Autowired
    RepositorioLoteProdutoVenda repositorioLoteProdutoVenda;
    @Autowired
    RepositorioProdutoVenda repositorioProdutoVenda;

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
        execucaoFaseProcessamento.setItemEstoqueInsumoConsumido(execucaoFaseProcessamento.getItemEstoqueInsumoConsumido().stream()
        .map(repositorioItemEstoqueInsumoConsumido::save)
        .map(ic -> {
            ItemEstoqueInsumo item = ic.getItemEstoqueInsumo();
            item.setInsumo(repositorioInsumo.save(repositorioInsumo.findById(item.getInsumo().getId()).get()));
            item.setQuantidade(item.getQuantidade() - ic.getQuantidadeConsumida());
            ic.setItemEstoqueInsumo(repositorioItemEstoqueInsumo.save(item));
            return this.repositorioItemEstoqueInsumoConsumido.save(ic);
        }).collect(Collectors.toList()));
        execucaoFaseProcessamento.setItemEstoqueInsumoProduzido(execucaoFaseProcessamento.getItemEstoqueInsumoProduzido().stream()
        .map(repositorioItemEstoqueInsumoProduzido::save)
        .map(ic -> {
            ItemEstoqueInsumo item = ic.getItemEstoqueInsumo();
            item.setInsumo(repositorioInsumo.save(repositorioInsumo.findById(item.getInsumo().getId()).get()));
            ic.setItemEstoqueInsumo(repositorioItemEstoqueInsumo.save(ic.getItemEstoqueInsumo()));
            return this.repositorioItemEstoqueInsumoProduzido.save(ic);
        }).collect(Collectors.toList()));
        execucaoFaseProcessamento.setItemProduzidoExecucao(execucaoFaseProcessamento.getItemProduzidoExecucao().stream()
        .map(repositorioItemProduzidoExecucao::save)
        .map(ic -> {
            ic.setLoteProdutoVenda(this.repositorioLoteProdutoVenda.save(ic.getLoteProdutoVenda()));
            return this.repositorioItemProduzidoExecucao.save(ic);
        }).collect(Collectors.toList()));
        
        return mapper.fromEntity(repositorioExecucaoFaseProcessamento.save(execucaoFaseProcessamento));
    }

    public ExecucaoFaseProcessamentoDto alterarExecucaoFaseProcessamento(ExecucaoFaseProcessamentoDto dto) {
        ExecucaoFaseProcessamento execucaoFaseProcessamento = this.repositorioExecucaoFaseProcessamento
                .getReferenceById(dto.getOS());
        execucaoFaseProcessamento.setDataInicio(dto.getDataInicio());
        execucaoFaseProcessamento.setDataTermino(dto.getDataTermino());
        return mapper.fromEntity(this.repositorioExecucaoFaseProcessamento.save(execucaoFaseProcessamento));
    }

}
