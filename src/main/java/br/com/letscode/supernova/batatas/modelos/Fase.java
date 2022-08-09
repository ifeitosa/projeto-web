package br.com.letscode.supernova.batatas.modelos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
@Entity
public class Fase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @NotNull
    private Processo processo;

    @OneToMany
    private List<InsumoConsumidoFase> insumosConsumidoFase;

    @Positive @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sequencia;
    @NotNull @NotEmpty @NotBlank
    private String nome;
    @NotNull @NotBlank @NotEmpty
    private String instrucoes;
    @NotNull @NotBlank @NotEmpty
    private String unidadeProducao;
    @Positive @NotNull
    private Double quantidadeProduzida;

    
}
