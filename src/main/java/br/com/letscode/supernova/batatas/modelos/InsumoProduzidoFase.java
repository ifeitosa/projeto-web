package br.com.letscode.supernova.batatas.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
public class InsumoProduzidoFase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /* @ManyToOne(cascade = {CascadeType.ALL}) */
    @ManyToOne
    private Fase fase; 
    @ManyToOne
    private Insumo insumo;
    @Column(nullable = false)
    private Double quantidadeProduzida;
    @Column(nullable = false)
    private String unidadeProducao;
}
