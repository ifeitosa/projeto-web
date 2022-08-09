package br.com.letscode.supernova.batatas.dto;

import javax.validation.constraints.NotNull;

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
    private Douuble estoqueMinimo;

    public InsumoDto(Insumo insumo) {
        this(insumo.getId(), insumo.getNome(), insumo.getUnidadeMedida(), insumo.getEstoqueMinimo());
    }
    
}
