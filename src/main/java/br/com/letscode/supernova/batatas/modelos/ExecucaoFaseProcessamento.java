package br.com.letscode.supernova.batatas.modelos;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.context.annotation.Lazy;

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
public class ExecucaoFaseProcessamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OS;

    @ManyToOne(targetEntity = Fase.class)
    private Fase fase;

    @Lazy(value = false)
    private LocalDate dataInicio;
    @Lazy(value = false)
    private LocalDate dataTermino;
    @OneToMany
    private List<ItemEstoqueInsumoConsumido> itemEstoqueInsumoConsumido;
    @OneToMany
    private List<ItemEstoqueInsumoProduzido> itemEstoqueInsumoProduzido;
    @OneToMany
    private List<ItemProduzidoExecucao> itemProduzidoExecucao;
}
