package br.com.letscode.supernova.batatas.dto;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
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
public class ItemEstoqueInsumoProduzidoDto {
    @Positive(message = "O identificador do item de estoque de insumo produzido deve ser positivo")
    private Long id;
    @NotNull(message = "O item de estoque de insumo produzido deve fazer referência a um item de estoque de insumo válido")
    @Valid
    private ItemEstoqueInsumoDto itemEstoqueInsumo;
    @PastOrPresent(message = "A data de registro do item de estoque de insumo produzido deve ser no máximo atual")
    private LocalDate dataRegistro;
    @NotNull(message = "A quantidade de item de estoque de insumo produzido deve ser positiva")
    @Positive(message = "A quantidade de item de estoque de insumo produzido deve ser positiva")
    private Double quantidadeProduzida;
    @NotNull(message = "A unidade de item de estoque de insumo produzido deve ser especificada")
    @NotBlank(message = "A unidade de item de estoque de insumo produzido deve ser especificada")
    @NotEmpty(message = "A unidade de item de estoque de insumo produzido deve ser especificada")
    private String unidadeConsumo;    
}
