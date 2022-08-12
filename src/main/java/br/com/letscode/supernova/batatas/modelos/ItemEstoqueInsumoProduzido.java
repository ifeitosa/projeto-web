package br.com.letscode.supernova.batatas.modelos;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

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
public class ItemEstoqueInsumoProduzido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    ItemEstoqueInsumo itemEstoqueInsumo;

    @OneToOne(targetEntity = ExecucaoFaseProcessamento.class)
    private ExecucaoFaseProcessamento execucao;

    LocalDateTime dataRegistro;

    Double quantidadeProduzida;

    String unidadeConsumo;    
}
