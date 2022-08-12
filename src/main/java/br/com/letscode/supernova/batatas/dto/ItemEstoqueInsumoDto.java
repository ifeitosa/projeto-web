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
@EqualsAndHashCode //(exclude = {"dataAquisicao", "dataValidade"})
@ToString
public class ItemEstoqueInsumoDto {
    private Long lote;
    private InsumoDto insumo;

    private Double quantidade;
    private LocalDate dataAquisicao;
    private LocalDate dataValidade;
    private String qualidade;
}
