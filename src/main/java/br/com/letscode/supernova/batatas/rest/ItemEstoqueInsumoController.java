package br.com.letscode.supernova.batatas.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import br.com.letscode.supernova.batatas.dto.ItemEstoqueInsumoDto;
import br.com.letscode.supernova.batatas.service.ItemEstoqueInsumoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/estoqueInsumo")
public class ItemEstoqueInsumoController {
    
    @Autowired
    private ItemEstoqueInsumoService service;
    
    @Cacheable(cacheNames = "itensDoEstoqueInsumo")
    @GetMapping
    @Operation(summary = "Obter itens do estoque de insumos", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ItemEstoqueInsumoDto.class))))})
    public List<ItemEstoqueInsumoDto> obterItensDoEstoque() {
        return service.obterItensDoEstoque();
    }

    @Cacheable(cacheNames = "itensDoEstoqueInsumoAtivos")
    @GetMapping("/ativos")
    @Operation(summary = "Obter insumos ativos", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ItemEstoqueInsumoDto.class))))})
    public List<ItemEstoqueInsumoDto> obterItensDoEstoqueAtivos() {
        return service.obterItensDoEstoqueAtivos();
    }

    @Cacheable(cacheNames = "itensDoEstoqueInsumoVencidos")
    @GetMapping("/vencidos")
    @Operation(summary = "Obter itens do estoque vencidos", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ItemEstoqueInsumoDto.class))))})
    public List<ItemEstoqueInsumoDto> obterItensDoEstoqueVencidos() {
        return service.obterItensDoEstoqueVencidos();
    }

    @Cacheable(cacheNames = "itensDoEstoqueInsumo")
    @GetMapping("/{id:\\d+}")
    @Operation(summary = "Obter um item do estoque de insumos", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ItemEstoqueInsumoDto.class)))),
        @ApiResponse(responseCode = "404", description = "Não encontrado", content = @Content)})
    public ResponseEntity<ItemEstoqueInsumoDto> obterItemDoEstoque(@PathVariable Long id){
        Optional<ItemEstoqueInsumoDto> dto = service.obterItemDoEstoque(id);
        if (dto.isPresent()) {
            return ResponseEntity.of(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Cacheable(cacheNames = "itensDoEstoqueInsumoSemEstoqueMinimo")
    @GetMapping("/semEstoqueMinimo")
    @Operation(summary = "Obter itens do estoque de insumos sem estoque mínimo", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ItemEstoqueInsumoDto.class))))})    
    public List<ItemEstoqueInsumoDto> obterItensSemEstoqueMinimo() {
        return service.obterItensSemEstoqueMinimo();
    }
    

    @CacheEvict(cacheNames = {"itensDoEstoqueInsumoSemEstoqueMinimo", "itensDoEstoqueInsumo", "itensDoEstoqueInsumoVencidos", "itensDoEstoqueInsumoAtivos"})
    @PutMapping("/{id:\\d+}")
    @Operation(summary = "Corrigir item do estoque de insumos", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemEstoqueInsumoDto.class))),
        @ApiResponse(description = "Não encontrado", responseCode = "404", content=@Content)})
    public ResponseEntity<ItemEstoqueInsumoDto> corrigirItemEstoqueInsumo(@Valid @PathVariable Long id, @RequestBody ItemEstoqueInsumoDto dto) {
        Optional<ItemEstoqueInsumoDto> retorno = this.service.corrigirItemEstoqueInsumo(id, dto);
        if (retorno.isPresent()) {
            return ResponseEntity.of(retorno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @CacheEvict(cacheNames = {"itensDoEstoqueInsumoSemEstoqueMinimo", "itensDoEstoqueInsumo", "itensDoEstoqueInsumoVencidos", "itensDoEstoqueInsumoAtivos"})
    @PostMapping
    @Operation(summary = "Inserir item no estoque de insumos", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ItemEstoqueInsumoDto.class)))})
    public ResponseEntity<ItemEstoqueInsumoDto> inserirItemEstoqueInsumo(@Valid @RequestBody ItemEstoqueInsumoDto dto) {
        Optional<ItemEstoqueInsumoDto> retorno = this.service.inserirItemEstoqueInsumo(dto);
        if (retorno.isPresent()) {
            return ResponseEntity.of(retorno);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
