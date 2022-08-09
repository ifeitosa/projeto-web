package br.com.letscode.supernova.batatas.dto;

import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.letscode.supernova.batatas.modelos.Fase;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class FaseDto {
    
    @Positive
    private Integer sequencia;
    @NotNull @NotEmpty @NotBlank
    private String nome;
    @NotNull @NotBlank @NotEmpty
    private String instrucoes;
    @NotNull @NotBlank @NotEmpty
    private String unidadeProducao;
    @Positive @NotNull
    private Double quantidadeProduzida;
    
    @NotNull
    private List<InsumoConsumidoFaseDto> insumosConsumidos = new ArrayList<>();
    
    public FaseDto(Fase fase) {
        this(fase.getSequencia(), fase.getNome(), fase.getInstrucoes(), 
            fase.getUnidadeProducao(), fase.getQuantidadeProduzida(), 
            fase.getInsumosConsumidos()
                .stream()
                .map(insumoConsumido - new InsumoConsumidoFaseDto(insumosConsumido))
                .collect(Collectors.toList()) );
    }

}
