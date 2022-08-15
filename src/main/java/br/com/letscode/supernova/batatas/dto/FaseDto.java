package br.com.letscode.supernova.batatas.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
    
    @Positive(message = "A sequência da fase deve ser especificada")
    private Integer sequencia;
    @NotNull(message = "A fase deve possuir um nome que a identifique")
    @NotEmpty(message = "A fase deve possuir um nome que a identifique")
    @NotBlank(message = "A fase deve possuir um nome que a identifique")
    private String nome;
    @NotNull(message = "Instruções para execucação da fase deve ser fornecidas")
    @NotBlank(message = "Instruções para execucação da fase deve ser fornecidas") 
    @NotEmpty(message = "Instruções para execucação da fase deve ser fornecidas")
    private String instrucoes;
    @NotNull(message = "Unidade de produção deve ser especificada")
    @NotBlank(message = "Unidade de produção deve ser especificada")
    @NotEmpty(message = "Unidade de produção deve ser especificada")
    private String unidadeProducao;
    @Positive(message = "Quantidade produzida deve ser positiva") 
    @NotNull(message = "Quantidade produzida na fase deve ser identificada")
    private Double quantidadeProduzida;
    
    @NotNull(message = "Lista de insumos consumidos deve ser fornecida")
    private List<InsumoConsumidoFaseDto> insumosConsumidos;
    @NotNull(message = "Lista de insumos produzidos deve ser fornecida")
    private List<InsumoProduzidoFaseDto> insumoProduzidos;
    
}
