package br.com.letscode.supernova.batatas.modelos;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.hibernate.type.LongType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class InsumoConsumidoFase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = {})
    private Fase fase;
    @OneToOne(cascade = {CascadeType.ALL})
    private Insumo insumo;
    @Positive @NotNull @Column(nullable = false)
    private Double quantidadeConsumida;
    @NotNull @NotBlank @NotEmpty @Column(nullable = false)
    private String unidadeConsumo;

    public InsumoConsumidoFase(Fase fase, Insumo insumo, Double quantidadeConsumida, String unidadeConsumo) {
        this(null, fase, insumo, quantidadeConsumida, unidadeConsumo);
    }
}

