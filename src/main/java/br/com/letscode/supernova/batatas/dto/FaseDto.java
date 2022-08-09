package br.com.letscode.supernova.batatas.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.letscode.supernova.batatas.modelos.Fase;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FaseDto {
    
    @Positive
    private Integer sequencia;
    @NotNull @NotEmpty @NotBlank
    private String nome;
    @NotNull @NotBlank @NotEmpty
    private String instrucoes;
    @NotNull @NotBlank @NotEmpty
    private String unidadeProducao;
    @Positive @NotNull
    private Double quantidadeProduzida;
    
    @NotNull
    private List<InsumoConsumidoFaseDto> insumosConsumidos = new ArrayList<>();
    
}