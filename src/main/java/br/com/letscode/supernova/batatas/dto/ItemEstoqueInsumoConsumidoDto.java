package br.com.letscode.supernova.batatas.dto;

import java.time.LocalDateTime;

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
    private Long id;
    private ItemEstoqueInsumoDto itemEstoqueInsumo;
    private ExecucaoFaseProcessamentoDto execucaoFaseProcessamento;
    private LocalDateTime dataRegistro;
    private Double quantidadeProduzida;
    private String unidadeConsumo;    
}