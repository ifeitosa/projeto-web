package br.com.letscode.supernova.batatas.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.supernova.batatas.dto.ExecucaoFaseProcessamentoDto;
import br.com.letscode.supernova.batatas.service.ExecucaoFaseProcessamentoService;

@RestController
@RequestMapping("/faseProcessamento")
public class ExecucaoFaseProcessamentoController {

    @Autowired ExecucaoFaseProcessamentoService service;

    @Cacheable(cacheNames = "obterExecucaoFaseProcessamento")
    @GetMapping
    public List<ExecucaoFaseProcessamentoDto> obter(@RequestParam("inicio") LocalDate inicio, @RequestParam("termino") LocalDate termino) {
        return this.service.encontrarPelaDataEntre(inicio, termino);
    }

    @Cacheable(cacheNames = "obterExecucaoFaseProcessamento")
    @GetMapping("/{id}")
    public ExecucaoFaseProcessamentoDto encontrarPeloId(@PathVariable Long id) {
        return this.service.encontrarPeloId(id);
    }

    @Cacheable(cacheNames = "obterExecucaoFaseProcessamentoPelaFase")
    @GetMapping("/fase/{id}")
    public List<ExecucaoFaseProcessamentoDto> encontrarPelaFase(@PathVariable Long id) {
        return this.service.encontrarPelaFase(id);
    }

    @CacheEvict(cacheNames = {"obterExecucaoFaseProcessamento", "obterExecucaoFaseProcessamentoPelaFase"})
    @PostMapping
    public ExecucaoFaseProcessamentoDto inserir(@RequestBody ExecucaoFaseProcessamentoDto dto) {
        return this.service.inserirExecucaoFaseProcessamento(dto);
    }

    @PutMapping("/{id:\\d+}")
    public ResponseEntity<ExecucaoFaseProcessamentoDto> corrigir(@PathVariable Long id, @RequestBody ExecucaoFaseProcessamentoDto dto) {        
        if (dto.getDataInicio() == null || dto.getDataTermino() == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(this.service.alterarExecucaoFaseProcessamento(id, dto));
    }
}
