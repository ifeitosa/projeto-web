package br.com.letscode.supernova.batatas.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;

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
    @PositiveOrZero(message = "O número da ordem de serviço deve ser não negativo")
    private Long OS;
    private IdFaseDto fase;
    
    @PastOrPresent(message = "A data de execução da fase deve ser no máximo a de hoje")
    private LocalDate dataInicio;
    @FutureOrPresent(message = "A data de término da execução da fase deve ser no mínimo a de hoje")
    private LocalDate dataTermino;
    @NotEmpty(message = "É necessário especificar os insumos necessários para a fase")
    @Valid
    private List<ItemEstoqueInsumoConsumidoDto> estoqueInsumoConsumido;
    @Valid
    @NotEmpty(message = "É necessário especificar os insumos produzidos pela fase")
    private List<ItemEstoqueInsumoProduzidoDto> estoqueInsumoProduzido;
    @Valid
    private List<ItemProduzidoExecucaoDto> itemProduzidoExecucao;
}
