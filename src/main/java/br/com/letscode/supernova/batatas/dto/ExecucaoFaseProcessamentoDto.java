package br.com.letscode.supernova.batatas.dto;

import java.time.LocalDate;
import java.util.List;

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
public class ExecucaoFaseProcessamentoDto {
    
    private Long OS;
    private IdFaseDto fase;
    
    private LocalDate dataInicio;
    
    private LocalDate dataTermino;
    private List<ItemEstoqueInsumoConsumidoDto> estoqueInsumoConsumido;
    private List<ItemEstoqueInsumoProduzidoDto> estoqueInsumoProduzido;
    private List<ItemProduzidoExecucaoDto> itemProduzidoExecucao;
}
