package br.com.letscode.supernova.batatas.dto;

import br.com.letscode.supernova.batatas.modelos.InsumoConsumidoFase;
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
    
    public InsumoConsumidoFaseDto(InsumoConsumidoFase insumoConsumido) {
        this(insumoConsumido.getId(), new InsumoDto(insumoConsumido.getInsumo()), 
            insumoConsumido.getQuantidadeConsumida(), insumoConsumido.getUnidadeConsumo());
    }
}
