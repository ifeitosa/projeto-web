package br.com.letscode.supernova.batatas.modelos;

import java.io.Serializable;

import javax.persistence.Column;

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
public class InsumoFase implements Serializable {
    @Column(name = "insumo")
    private Insumo insumo;
    @Column(name = "fase")
    private Fase fase;
}
