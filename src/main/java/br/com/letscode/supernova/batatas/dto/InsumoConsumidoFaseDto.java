package br.com.letscode.supernova.batatas.dto;

import javax.validation.Valid;
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
public class InsumoConsumidoFaseDto {

    @Valid
    @NotNull(message = "O item de insumo consumido deve fazer referência a uma insumo listado")
    private InsumoDto insumo;
    @NotNull(message = "A quantidade de insumo consumida deve ser positiva") 
    @Positive(message = "A quantidade de insumo consumido deve ser positiva")
    private Double quantidadeConsumida;
    @NotNull(message = "A unidade de consumo do insumo deve ser especificada")
    @NotEmpty(message = "A unidade de consumo do insumo deve ser especificada")
    @NotBlank(message = "A unidade de consumo do insumo deve ser especificada")
    private String unidadeConsumo;
   
}
