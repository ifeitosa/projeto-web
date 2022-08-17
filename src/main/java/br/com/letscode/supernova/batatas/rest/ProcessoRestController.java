package br.com.letscode.supernova.batatas.rest;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.supernova.batatas.dto.ErrosDto;
import br.com.letscode.supernova.batatas.dto.ProcessoDto;
import br.com.letscode.supernova.batatas.service.ProcessoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(path = "/processo")
public class ProcessoRestController {

    @Autowired
    private ProcessoService service;

    @Cacheable(cacheNames = "obterProcessos")
    @GetMapping
    @Operation(summary = "Obter lista de processos", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProcessoDto.class)))),
        @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content)})
    public List<ProcessoDto> obterProcessos() {
        return this.service.obterProcessos();
    }

    @PostMapping
    @Operation(summary = "Adicionar um processo", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessoDto.class))),
        @ApiResponse(responseCode = "400", description = "Requisição mal formada", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrosDto.class)))})
    public ResponseEntity<ProcessoDto> adicionarProceso(@Valid @RequestBody ProcessoDto dto) {
        return ResponseEntity.of(Optional.of(this.service.adicionarProcesso(dto)));
    }

    @CacheEvict(cacheNames = "obterProcessos")
    @PutMapping(path = "/{id:\\d+}")
    @Operation(summary = "Corrigir um processo", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessoDto.class))),
        @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content)})
    public ResponseEntity<ProcessoDto> corrigirProcesso(@PathVariable Long id, @Valid @RequestBody ProcessoDto dto) {
        ProcessoDto retorno = this.service.corrigirProcesso(id, dto);
        if (retorno == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.of(Optional.of(retorno));
        }
    }

    @Cacheable(cacheNames = "obterProcessos")
    @GetMapping(path = "/{id:\\d+}")
    @Operation(summary = "Obter um processo", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProcessoDto.class))),
        @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content)})
    public ResponseEntity<ProcessoDto> obterProcesso(@PathVariable Long id) {
        Objects.requireNonNull(id);
        Optional<ProcessoDto> dto = this.service.obterProcesso(id);
        if (dto.isPresent()) {
            return ResponseEntity.of(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id:\\d+}")
    @CacheEvict(cacheNames = "obterProcessos")
    @Operation(summary = "Deletar um processo", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200"),
        @ApiResponse(responseCode = "404", description = "Processo não encontrado")})
    public void deletarProcesso(@PathVariable Long id) {
        this.service.deletarProcesso(id);
    }

}
