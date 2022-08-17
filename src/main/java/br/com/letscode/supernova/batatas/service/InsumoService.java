package br.com.letscode.supernova.batatas.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.letscode.supernova.batatas.dto.ErrosDto;
import br.com.letscode.supernova.batatas.dto.InsumoDto;
import br.com.letscode.supernova.batatas.mapper.InsumoMapper;
import br.com.letscode.supernova.batatas.modelos.Insumo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioInsumo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@Service
public class InsumoService {

    @Autowired
    private RepositorioInsumo repositorio;

    @Operation(summary = "Obter insumo específico", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InsumoDto.class))),
        @ApiResponse(description = "Não encontrado", responseCode = "404", content=@Content(mediaType = "application/json", schema = @Schema(implementation = InsumoDto.class)))})
    public Optional<InsumoDto> obterInsumo(Long id) {
        Optional<InsumoDto> retorno = this.repositorio.findById(id).map(InsumoMapper::fromEntity);
        return retorno;
    }

    @Operation(summary = "Obter todos itens do estoque de insumos", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = InsumoDto.class))))})
    public List<InsumoDto> obterInsumos() {
        return this.repositorio.findAll().stream().map(InsumoMapper::fromEntity).collect(Collectors.toList());
    }

    @Operation(summary = "Obter itens do estoque de insumos", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InsumoDto.class))),
        @ApiResponse(description = "Requisição falha", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrosDto.class)))})
    public Optional<InsumoDto> corrigirInsumo(Long id, InsumoDto dto) {
        Optional<Insumo> talvezInsumo = this.repositorio.findById(id);
        if (talvezInsumo.isPresent()) {
            Insumo insumo = talvezInsumo.get();
            insumo.setEstoqueMinimo(dto.getEstoqueMinimo());
            insumo.setNome(dto.getNome());
            insumo.setUnidadeMedida(dto.getUnidadeMedida());
            return Optional.of(InsumoMapper.fromEntity(this.repositorio.save(insumo)));
        } else {
            return Optional.ofNullable(null);
        }
    }

    @Operation(summary = "Obter itens do estoque de insumos", responses = {
        @ApiResponse(description = "Sucesso", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = InsumoDto.class))),
        @ApiResponse(description = "Requisição falha", responseCode = "400", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrosDto.class)))})
    public Optional<InsumoDto> inserir(InsumoDto dto) {
        Insumo insumo = InsumoMapper.toEntity(dto);
        insumo = this.repositorio.save(insumo);
        return Optional.of(InsumoMapper.fromEntity(insumo));
    }
    
}
