package br.com.letscode.supernova.batatas.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.letscode.supernova.batatas.dto.FaseDto;
import br.com.letscode.supernova.batatas.dto.InsumoConsumidoFaseDto;
import br.com.letscode.supernova.batatas.dto.InsumoDto;
import br.com.letscode.supernova.batatas.dto.ProcessoDto;
import br.com.letscode.supernova.batatas.modelos.Fase;
import br.com.letscode.supernova.batatas.modelos.Insumo;
import br.com.letscode.supernova.batatas.modelos.InsumoConsumidoFase;
import br.com.letscode.supernova.batatas.modelos.Processo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioFase;
import br.com.letscode.supernova.batatas.repositorios.RepositorioInsumo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioInsumoConsumidoFase;
import br.com.letscode.supernova.batatas.repositorios.RepositorioProcesso;
import br.com.letscode.supernova.batatas.repositorios.RepositorioProdutoVenda;
import br.com.letscode.supernova.batatas.service.ProcessoService;

@Component
public class BeanInicializacao {

    /* private RepositorioProdutoVenda repoProdVenda;
    private RepositorioInsumo repoInsumo;
    private RepositorioFase repoFase;
    private RepositorioProcesso repoProc;
    private RepositorioInsumoConsumidoFase repoConsumo; */
    @Autowired
    ProcessoService service;
/* 
    public BeanInicializacao(RepositorioProcesso repoProc, RepositorioFase repoFase, RepositorioInsumoConsumidoFase repoConsumo, RepositorioInsumo repoInsumo, RepositorioProdutoVenda repoProdVenda) {
        this.repoProc = repoProc;
        this.repoFase = repoFase;
        this.repoConsumo = repoConsumo;
        this.repoInsumo = repoInsumo;
        this.repoProdVenda = repoProdVenda;
    } */


    private static final InsumoDto[] insumos = {
        new InsumoDto("Insumo 1", "kg", 10.0D),
        new InsumoDto("Insumo 2", "L", 1.0D),
        new InsumoDto("Insumo 3", "g", 100.0D),
        new InsumoDto("Insumo 4", "m", 5.0D)
    };
    private static final InsumoConsumidoFaseDto[] consumidos = {
        new InsumoConsumidoFaseDto(insumos[0], 4.0D, "kg"),
        new InsumoConsumidoFaseDto(insumos[1], 3.0D, "mL"),
        new InsumoConsumidoFaseDto(insumos[2], 18.0D, "g"),
        new InsumoConsumidoFaseDto(insumos[3], 2.1D, "m")
    };

    private static final FaseDto[] fases = {
        new FaseDto(1, "Fase 1", "Instrução da fase 1", "kg", 100.0D, List.of(consumidos[0])),
        new FaseDto(2, "Fase 2", "Instrução da fase 2", "g", 3.2D, List.of(consumidos[1], consumidos[2])),
        new FaseDto(3, "Fase 3", "Instrução da fase 3", "m", 2.1D, List.of(consumidos[3]))
    };
    private static final ProcessoDto processo = 
        new ProcessoDto(Long.valueOf(3), "Processo de teste", 
                "Este é um processo de teste", 
                LocalDateTime.now(), 
                "Italo", 
                List.of(fases));

    @EventListener
    @Transactional
    public void onStartup(WebServerInitializedEvent ignored) {
        /* Processo processo = new Processo(null, "Processo de teste", "Este é um teste de processo", LocalDateTime.now(),
            "Italo", new ArrayList<>());
        processo = this.repoProc.save(processo);
        
        Insumo insumo1 = new Insumo(null, "Insumo 1", "kg", 10.0D);
        Insumo insumo2 = new Insumo(null, "Insumo 2", "kg", 10.0D);
        Insumo insumo3 = new Insumo(null, "Insumo 3", "kg", 10.0D);
        insumo1 = this.repoInsumo.save(insumo1);
        insumo2 = this.repoInsumo.save(insumo2);
        insumo3 = this.repoInsumo.save(insumo3);

        Fase fase1 = new Fase(null, processo, 1, "Fase 1 teste", "Testagem da fase 1", "kg", 10.0D, new ArrayList<>());
        Fase fase2 = new Fase(null, processo, 2, "Fase 2 teste", "Testagem da fase 2", "l", 10.0D, new ArrayList<>());
        processo.getFases().add(fase1);
        processo.getFases().add(fase2);
        processo.setFases(this.repoFase.saveAll(processo.getFases()));

        InsumoConsumidoFase consumo1 = new InsumoConsumidoFase(null, insumo1, 10.0D, insumo1.getUnidadeMedida());
        InsumoConsumidoFase consumo2 = new InsumoConsumidoFase(null, insumo2, 8.0D, insumo2.getUnidadeMedida());
        InsumoConsumidoFase consumo3 = new InsumoConsumidoFase(null, insumo3, 7.0D, insumo3.getUnidadeMedida());
        
        fase1.getInsumosConsumidos().add(consumo1);
        fase2.getInsumosConsumidos().add(consumo2);
        fase2.getInsumosConsumidos().add(consumo3);
        
        this.repoConsumo.saveAll(List.of(consumo1, consumo2, consumo3)); */

        //this.service.adicionarProcesso(processo);


    }
    
}
