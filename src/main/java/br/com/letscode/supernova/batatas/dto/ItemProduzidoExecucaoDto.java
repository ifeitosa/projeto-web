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
public class ItemProduzidoExecucaoDto {
    @Positive(message = "O identificador de item produzido na execução deve ser positivo")
    private Long id;
    @NotNull(message = "O item produzido na execução deve fazer referência a um lote de produto para venda válido")
    @Valid
    private LoteProdutoVendaDto loteProdutoVenda;
    @PastOrPresent(message = "A data de registro do item produzido na execução deve ser no máximo atual")
    private LocalDate dataRegistro;
    @Positive(message = "A quantidade do produto de venda produzido deve ser positiva")
    @NotNull(message = "A quantidade do produto de venda produzido deve ser positiva")
    private Double quantidadeProduzida;
    @NotNull(message = "A unidade de mensuração do produto de venda deve ser especificada")
    @NotBlank(message = "A unidade de mensuração do produto de venda deve ser especificada")
    @NotEmpty(message = "A unidade de mensuração do produto de venda deve ser especificada")
    private String unidadeProducao;
}
