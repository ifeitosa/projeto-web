package br.com.letscode.supernova.batatas.modelos;

import java.time.ZonedDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class ItemEstoqueInsumo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lote;

    @ManyToOne(targetEntity = Insumo.class,fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private Insumo insumo;

    private Double quantidade;
    private ZonedDateTime dataAquisicao;
    private ZonedDateTime dataValidade;
    private String qualidade;
    
}
