package br.com.letscode.supernova.batatas.dto;

import java.time.ZonedDateTime;

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
public class ItemEstoqueInsumoDto {
    private Long lote;
    private InsumoDto insumo;

    private Double quantidade;
    private ZonedDateTime dataAquisicao;
    private ZonedDateTime dataValidade;
    private String qualidade;
}
