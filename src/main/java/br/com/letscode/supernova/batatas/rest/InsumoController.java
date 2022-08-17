package br.com.letscode.supernova.batatas.rest;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.supernova.batatas.dto.ErrosDto;
import br.com.letscode.supernova.batatas.dto.InsumoDto;
import br.com.letscode.supernova.batatas.service.InsumoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/insumo")
public class InsumoController {

    @Autowired
    private InsumoService service;
    
    @Cacheable(cacheNames = "obterInsumos")
    @GetMapping("/{id:\\d+}")
    @Operation(summary = "Obter um insumo", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InsumoDto.class))),
        @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrosDto.class)))})
    public ResponseEntity<InsumoDto> obterInsumo(@PathVariable Long id) {
        Optional<InsumoDto> retorno = this.service.obterInsumo(id);
        if (retorno.isPresent()) {
            return ResponseEntity.of(retorno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Cacheable(cacheNames = "obterInsumos")
    @GetMapping
    @Operation(summary = "Obter lista de insumos", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = InsumoDto.class)))),
        @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrosDto.class)))})
    public List<InsumoDto> obterInsumos() {
        return this.service.obterInsumos();
    }

    @CacheEvict(cacheNames = "obterInsumos")
    @PutMapping("/{id:\\d+}")
    @Operation(summary = "Corrigir um insumo", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InsumoDto.class))),
        @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrosDto.class)))})
    public ResponseEntity<InsumoDto> corrigirInsumo(@PathVariable Long id, @RequestBody InsumoDto dto) {
        Optional<InsumoDto> retorno = this.service.corrigirInsumo(id, dto);
        if (retorno.isPresent()) {
            return ResponseEntity.of(retorno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CacheEvict(cacheNames = "obterInsumos")
    @PostMapping
    @Operation(summary = "Inserir um insumo", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InsumoDto.class)))})
    public ResponseEntity<InsumoDto> inserirInsumo(@RequestBody InsumoDto insumo) {
        Optional<InsumoDto> retorno = this.service.inserir(insumo);
        if (retorno.isPresent()) {
            return ResponseEntity.of(retorno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}
