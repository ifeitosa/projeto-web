package br.com.letscode.supernova.batatas.modelos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
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
@EqualsAndHashCode
@ToString
@Entity
@IdClass(value = InsumoFase.class)
public class InsumoConsumidoFase {
    @Id @ManyToOne @Cascade(CascadeType.ALL)
    private Fase fase;
    @Id @ManyToOne
    @Cascade(CascadeType.ALL)
    private Insumo insumo;
    @Positive @NotNull @Column(nullable = false)
    private Double quantidadeConsumida;
    @NotNull @NotBlank @NotEmpty @Column(nullable = false)
    private String unidadeConsumo;
}

