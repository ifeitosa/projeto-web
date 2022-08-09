package br.com.letscode.supernova.batatas.dto;

import javax.validation.constraints.NotNull;

import br.com.letscode.supernova.batatas.modelos.Insumo;
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
public class InsumoDto {

    @NotNull
    private Long id;
    @NotNull
    private String nome;
    @NotNull
    private String unidadeMedida;
    @NotNull
    private Double estoqueMinimo;
    
}
