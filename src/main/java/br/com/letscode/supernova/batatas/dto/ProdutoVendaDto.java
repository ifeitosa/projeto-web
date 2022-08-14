package br.com.letscode.supernova.batatas.dto;

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
public class ProdutoVendaDto {
    private Long id;
    private IdProcesso processo;
    private String descricao;
    private String unidadeMedida;
    private Double quantidade;
    private Double custoProducao;
}
