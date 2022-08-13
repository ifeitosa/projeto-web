package br.com.letscode.supernova.batatas.modelos;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class ItemProduzidoExecucao {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = ExecucaoFaseProcessamento.class)
    private ExecucaoFaseProcessamento execucaoFaseProcessamento;

    @OneToOne(targetEntity = LoteProdutoVenda.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private LoteProdutoVenda loteProdutoVenda;

    private LocalDate dataRegistro;

    private Double quantidadeProduzida;
    private String unidadeProducao;
    
}
