package br.com.letscode.supernova.batatas.dto;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.Future;
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
@EqualsAndHashCode //(exclude = {"dataAquisicao", "dataValidade"})
@ToString
public class ItemEstoqueInsumoDto {
    @Positive(message = "O lote do item de estoque de insumo deve ser positivo")
    private Long lote;
    @Valid
    @NotNull(message = "O item de estoque de insumo deve fazer referência a um item de insumo válido")
    private InsumoDto insumo;

    @NotNull(message = "A quantidade do item de estoque de insumo deve ser positiva")
    @Positive(message = "A quantidade do item de estoque de insumo deve ser positiva")
    private Double quantidade;
    @PastOrPresent(message = "A data de aquisição do item de estoque de insumo deve ser no máximo atual")
    private LocalDate dataAquisicao;
    @Future(message = "A data de validade do item de estoque de insumo deve ser no futuro")
    private LocalDate dataValidade;
    @NotNull(message = "A qualidade do item de estoque de insumo deve ser especificada")
    @NotBlank(message = "A qualidade do item de estoque de insumo deve ser especificada")
    @NotEmpty(message = "A qualidade do item de estoque de insumo deve ser especificada")
    private String qualidade;
}
