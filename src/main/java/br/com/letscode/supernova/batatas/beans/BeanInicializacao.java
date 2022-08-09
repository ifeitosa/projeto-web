package br.com.letscode.supernova.batatas.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import br.com.letscode.supernova.batatas.modelos.Fase;
import br.com.letscode.supernova.batatas.modelos.Insumo;
import br.com.letscode.supernova.batatas.modelos.InsumoConsumidoFase;
import br.com.letscode.supernova.batatas.modelos.Processo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioFase;
import br.com.letscode.supernova.batatas.repositorios.RepositorioInsumo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioInsumoConsumidoFase;
import br.com.letscode.supernova.batatas.repositorios.RepositorioProcesso;
import br.com.letscode.supernova.batatas.repositorios.RepositorioProdutoVenda;

@Component
public class BeanInicializacao {

    private RepositorioProdutoVenda repoProdVenda;
    private RepositorioInsumo repoInsumo;
    private RepositorioFase repoFase;
    private RepositorioProcesso repoProc;
    private RepositorioInsumoConsumidoFase repoConsumo;

    public BeanInicializacao(RepositorioProcesso repoProc, RepositorioFase repoFase, RepositorioInsumoConsumidoFase repoConsumo, RepositorioInsumo repoInsumo, RepositorioProdutoVenda repoProdVenda) {
        this.repoProc = repoProc;
        this.repoFase = repoFase;
        this.repoConsumo = repoConsumo;
        this.repoInsumo = repoInsumo;
        this.repoProdVenda = repoProdVenda;
    }

    @EventListener
    @Transactional
    public void onStartup(WebServerInitializedEvent ignored) {
        Processo processo = new Processo(null, "Processo de teste", "Este é um teste de processo", LocalDateTime.now(),
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
        
        this.repoConsumo.saveAll(List.of(consumo1, consumo2, consumo3));

    }
    
}
