package br.com.letscode.supernova.batatas.beans;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.letscode.supernova.batatas.dto.FaseDto;
import br.com.letscode.supernova.batatas.dto.InsumoConsumidoFaseDto;
import br.com.letscode.supernova.batatas.dto.InsumoDto;
import br.com.letscode.supernova.batatas.dto.InsumoProduzidoFaseDto;
import br.com.letscode.supernova.batatas.dto.ProcessoDto;
import br.com.letscode.supernova.batatas.service.ProcessoService;

@Component
public class BeanInicializacao {

    @Autowired
    private ProcessoService service;

    private static final InsumoDto[] insumos = {
            new InsumoDto(null, "Insumo 1", "kg", 10.0D),
            new InsumoDto(null, "Insumo 2", "L", 1.0D),
            new InsumoDto(null, "Insumo 3", "g", 100.0D),
            new InsumoDto(null, "Insumo 4", "m", 5.0D),
            new InsumoDto(null, "Insumo 5", "L", 98.3D),
            new InsumoDto(null, "Insumo 6", "T", 109.d),
            new InsumoDto(null, "Insumo 7", "GL", 10.2D)

    };
    private static final InsumoConsumidoFaseDto[] consumidos = {
            new InsumoConsumidoFaseDto(insumos[0], 4.0D, "kg"),
            new InsumoConsumidoFaseDto(insumos[1], 3.0D, "mL"),
            new InsumoConsumidoFaseDto(insumos[2], 18.0D, "g"),
            new InsumoConsumidoFaseDto(insumos[3], 2.1D, "m")
    };

    private static final InsumoProduzidoFaseDto[] produzidos = {
        new InsumoProduzidoFaseDto(insumos[4], 18.9D, "L"),
        new InsumoProduzidoFaseDto(insumos[5], 17.3D, "m"),
        new InsumoProduzidoFaseDto(insumos[6], 18.3D, "g")
    };

    private static final FaseDto[] fases = {
            new FaseDto(1, "Fase 1", "Instrução da fase 1", "kg", 100.0D, List.of(consumidos[0]), List.of(produzidos[0])),
            new FaseDto(2, "Fase 2", "Instrução da fase 2", "g", 3.2D, List.of(consumidos[1], consumidos[2]), List.of(produzidos[1])),
            new FaseDto(3, "Fase 3", "Instrução da fase 3", "m", 2.1D, List.of(consumidos[3]), List.of(produzidos[2]))
    };
    private static final ProcessoDto processo = new ProcessoDto(Long.valueOf(3), "Processo de teste",
            "Este é um processo de teste",
            LocalDate.now(),
            "Italo",
            List.of(fases));

    @EventListener
    public void onStartup(WebServerInitializedEvent ignored) throws JsonProcessingException {
        this.service.adicionarProcesso(processo);
        
        /*
         * Processo processo = new Processo(null, "Processo de teste",
         * "Este é um teste de processo", LocalDate.now(),
         * "Italo", new ArrayList<>());
         * processo = this.repoProc.save(processo);
         * 
         * Insumo insumo1 = new Insumo(null, "Insumo 1", "kg", 10.0D);
         * Insumo insumo2 = new Insumo(null, "Insumo 2", "kg", 10.0D);
         * Insumo insumo3 = new Insumo(null, "Insumo 3", "kg", 10.0D);
         * insumo1 = this.repoInsumo.save(insumo1);
         * insumo2 = this.repoInsumo.save(insumo2);
         * insumo3 = this.repoInsumo.save(insumo3);
         * 
         * Fase fase1 = new Fase(null, 1, "Fase 1 teste", "Testagem da fase 1", "kg",
         * 10.0D, new ArrayList<>());
         * Fase fase2 = new Fase(null, 2, "Fase 2 teste", "Testagem da fase 2", "l",
         * 10.0D, new ArrayList<>());
         * processo.getFases().add(fase1);
         * processo.getFases().add(fase2);
         * processo.setFases(this.repoFase.saveAll(processo.getFases()));
         * 
         * InsumoConsumidoFase consumo1 = new InsumoConsumidoFase(null, insumo1, 10.0D,
         * insumo1.getUnidadeMedida());
         * InsumoConsumidoFase consumo2 = new InsumoConsumidoFase(null, insumo2, 8.0D,
         * insumo2.getUnidadeMedida());
         * InsumoConsumidoFase consumo3 = new InsumoConsumidoFase(null, insumo3, 7.0D,
         * insumo3.getUnidadeMedida());
         * 
         * fase1.getInsumosConsumidos().add(consumo1);
         * fase2.getInsumosConsumidos().add(consumo2);
         * fase2.getInsumosConsumidos().add(consumo3);
         * 
         * this.repoConsumo.saveAll(List.of(consumo1, consumo2, consumo3));
         */
    }

}
