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

import br.com.letscode.supernova.batatas.dto.ErrosDto;
import br.com.letscode.supernova.batatas.dto.ExecucaoFaseProcessamentoDto;
import br.com.letscode.supernova.batatas.service.ExecucaoFaseProcessamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/faseProcessamento")
public class ExecucaoFaseProcessamentoController {

    @Autowired ExecucaoFaseProcessamentoService service;

    @Cacheable(cacheNames = "obterExecucaoFaseProcessamento")
    @GetMapping
    @Operation(summary = "Obter as execuções de fases dos processos entre das datas inicial e final", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExecucaoFaseProcessamentoDto.class))),
        @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrosDto.class)))},
        parameters = {@Parameter(required = false, name = "inicio", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = LocalDate.class))),
                      @Parameter(required = false, name = "termino", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = LocalDate.class)))})
    public List<ExecucaoFaseProcessamentoDto> obter(@RequestParam("inicio") LocalDate inicio, @RequestParam("termino") LocalDate termino) {
        return this.service.encontrarPelaDataEntre(inicio, termino);
    }

    @Cacheable(cacheNames = "obterExecucaoFaseProcessamento")
    @GetMapping("/{id}")
    @Operation(summary = "Obter uma execuções de fases dos processos pelo identificador", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExecucaoFaseProcessamentoDto.class))),
        @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrosDto.class)))})
    public ExecucaoFaseProcessamentoDto encontrarPeloId(@PathVariable Long id) {
        return this.service.encontrarPeloId(id);
    }

    @Cacheable(cacheNames = "obterExecucaoFaseProcessamentoPelaFase")
    @GetMapping("/fase/{id}")
    @Operation(summary = "Obter as execuções de fases dos processos por um id de fase", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExecucaoFaseProcessamentoDto.class))),
        @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrosDto.class)))},
        parameters = {@Parameter(name = "id", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = Long.class)))})
    public List<ExecucaoFaseProcessamentoDto> encontrarPelaFase(@PathVariable Long id) {
        return this.service.encontrarPelaFase(id);
    }

    @CacheEvict(cacheNames = {"obterExecucaoFaseProcessamento", "obterExecucaoFaseProcessamentoPelaFase"})
    @PostMapping
    @Operation(summary = "Inserir uma execução de fase de processo", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExecucaoFaseProcessamentoDto.class))),
        @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrosDto.class)))},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExecucaoFaseProcessamentoDto.class))))
    public ExecucaoFaseProcessamentoDto inserir(@RequestBody ExecucaoFaseProcessamentoDto dto) {
        return this.service.inserirExecucaoFaseProcessamento(dto);
    }

    @PutMapping("/{id:\\d+}")
    @Operation(summary = "Corrige uma execução de fase de processo", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExecucaoFaseProcessamentoDto.class))),
        @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrosDto.class)))},
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExecucaoFaseProcessamentoDto.class))),
        parameters = {@io.swagger.v3.oas.annotations.Parameter(name = "id", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class)))})
    public ResponseEntity<ExecucaoFaseProcessamentoDto> corrigir(@PathVariable Long id, @RequestBody ExecucaoFaseProcessamentoDto dto) {        
        if (dto.getDataInicio() == null || dto.getDataTermino() == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(this.service.alterarExecucaoFaseProcessamento(id, dto));
    }
}
