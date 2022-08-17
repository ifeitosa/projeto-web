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
@EqualsAndHashCode
@ToString
public class LoteProdutoVendaDto {
    @Positive(message = "O lote do produto de venda deve ser positivo")
    private Long lote;
    @NotNull(message = "O produto de venda referido pelo lote deve ser válido")
    @Valid
    private ProdutoVendaDto produtoVenda;
    @NotNull(message = "A data de validade do lote de produto de venda deve ser futura")
    @PastOrPresent(message = "A data de fabricação do lote de produto de venda deve ser no máximo atual")
    private LocalDate dataFabricacao;
    @NotNull(message = "A data de validade do lote de produto de venda deve ser futura")
    @Future(message = "A data de validade do lote de produto de venda deve ser futura")
    private LocalDate dataValidade;
    @Positive(message = "O preço de venda do lote de produto deve ser positivo")
    @NotNull(message = "O preço de venda do lote de produto deve ser positivo")
    private Double precoVenda;
    @NotNull(message = "A unidade de estocagem do lote de produto de venda deve ser especificada")
    @NotBlank(message = "A unidade de estocagem do lote de produto de venda deve ser especificada")
    @NotEmpty(message = "A unidade de estocagem do lote de produto de venda deve ser especificada")
    private String unidadeEstoque;
    @NotNull(message = "A quantidade de estocagem do lote de produto de venda deve ser especificada")
    @Positive(message = "A quantidade de estocagem do lote de produto de venda deve ser especificada")
    private Double quantidadeEstoque;
}
