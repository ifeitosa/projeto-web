package br.com.letscode.supernova.batatas.dto;

import java.time.LocalDate;

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
    private Long lote;
    private ProdutoVendaDto produtoVenda;
    private LocalDate dataFabricacao;
    private LocalDate dataValidade;
    private Double precoVenda;
    private String unidadeEstoque;
    private Double quantidadeEstoque;
}
