package br.com.letscode.supernova.batatas.modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
    @NotNull @ManyToOne
    private Processo processo;

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
    
    @ElementCollection(targetClass = InsumoConsumidoFase.class, fetch = FetchType.LAZY)
    @CollectionTable(joinColumns = @JoinColumn(name = "fase", table = "INSUMOS_CONSUMIDOS_FASE"))    
    @OrderBy(value = "id")
    @Cascade(CascadeType.ALL)
    private List<InsumoConsumidoFase> insumosConsumidos = new ArrayList<>();
    
}
