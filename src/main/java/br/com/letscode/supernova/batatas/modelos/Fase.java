package br.com.letscode.supernova.batatas.modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@EqualsAndHashCode(exclude = "insumosConsumidos")
@ToString
@Entity
public class Fase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Positive @GeneratedValue(strategy = GenerationType.AUTO) @Column(nullable = false)
    private Integer sequencia;
    @NotNull @NotEmpty @NotBlank @Column(nullable = false)
    private String nome;
    @NotNull @NotBlank @NotEmpty @Column(nullable = false)
    private String instrucoes;
    @NotNull @NotBlank @NotEmpty @Column(nullable = false)
    private String unidadeProducao;
    @Positive @NotNull @Column(nullable = false)
    private Double quantidadeProduzida;
    
    

    
    //@ElementCollection(fetch = FetchType.EAGER) @CollectionTable(joinColumns =  @JoinColumn(name = "fase", table = "INSUMO_CONSUMIDO_FASE"))
    
    //@Cascade({CascadeType.ALL})
    @OneToMany(mappedBy = "fase", cascade = {CascadeType.ALL})
    private List<InsumoConsumidoFase> insumosConsumidos;
    
}