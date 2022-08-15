package br.com.letscode.supernova.batatas.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

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
public class InsumoDto {
    Long id;
    @NotNull(message = "O insumo deve ser identificado por um nome válido")
    @NotBlank(message = "O insumo deve ser identificado por um nome válido")
    @NotEmpty(message = "O insumo deve ser identificado por um nome válido")
    private String nome;
    @NotNull(message = "A unidade de medida do insumo deve ser especificada")
    @NotEmpty(message = "A unidade de medida do insumo deve ser especificada")
    @NotBlank(message = "A unidade de medida do insumo deve ser especificada")
    private String unidadeMedida;
    @NotNull(message = "O volume do estoque mínimo deve ser especificado")
    @PositiveOrZero(message = "O volume do estuque mínimo do insumo deve ser não negativo")
    private Double estoqueMinimo;

}
