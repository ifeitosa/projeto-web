package br.com.letscode.supernova.batatas.dto;

import java.time.LocalDate;

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
public class ItemEstoqueInsumoConsumidoDto {
    @Positive(message = "O identificador do item de estoque de insumo consumido deve ser positivo")
    private Long id;
    @NotNull(message = "O item de estoque de insumo deve ser referenciado")
    private ItemEstoqueInsumoDto itemEstoqueInsumo;
    @PastOrPresent(message = "A data de registro deve ser atual ou anterior")
    private LocalDate dataRegistro;
    @Positive(message = "A quantidade do item de estoque de insumo consumido deve ser positiva")
    private Double quantidadeProduzida;
    @NotNull(message = "A unidade de consumo do item de estoque de insumo deve ser especificada")
    @NotEmpty(message = "A unidade de consumo do item de estoque de insumo deve ser especificada")
    @NotBlank(message = "A unidade de consumo do item de estoque de insumo deve ser especificada")
    private String unidadeConsumo;    
}
