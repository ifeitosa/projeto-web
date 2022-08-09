package br.com.letscode.supernova.batatas.dto;

import javax.validation.constraints.NotNull;

import br.com.letscode.supernova.batatas.modelos.InsumoConsumidoFase;
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
public class InsumoConsumidoFaseDto {

    @NotNull
    private Long id;
    @NotNull
    private InsumoDto insumo;
    @NotNull
    private Double quantidadeConsumida;
    @NotNull
    private String unidadeConsumo;
   
}
