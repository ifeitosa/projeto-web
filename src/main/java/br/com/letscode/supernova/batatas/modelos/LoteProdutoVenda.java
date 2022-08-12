package br.com.letscode.supernova.batatas.modelos;

import java.time.LocalDate;

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
public class LoteProdutoVenda {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lote;

    @ManyToOne
    private ProdutoVenda produtoVenda;

    private LocalDate dataFabricacao;
    private LocalDate dataValidade;
    private Double precoVenda;
    private String unidadeEstoque;
    private Double quantidadeEstoque;
    
}
