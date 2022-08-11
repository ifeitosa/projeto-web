package br.com.letscode.supernova.batatas.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.letscode.supernova.batatas.modelos.Processo;
import br.com.letscode.supernova.batatas.modelos.ProdutoVenda;

@DataJpaTest
public class RepositorioProdutoVendaTest {
    
    private RepositorioProcesso repositorioProcesso;
    private RepositorioProdutoVenda repositorioProdutoVenda;

    public RepositorioProdutoVendaTest(@Autowired RepositorioProcesso repositorioProcesso, @Autowired  RepositorioProdutoVenda repositorioProdutoVenda) {
        this.repositorioProcesso = repositorioProcesso;
        this.repositorioProdutoVenda = repositorioProdutoVenda;
    }

    @BeforeEach
    public void reiniciaDados() {
        Processo processo1 = new Processo(null, "Preparar batata-palha levemente salgada sabor bacon", "Processo de saborização da batata-palha", ZonedDateTime.now().minusMonths(2), "Cláudio", new ArrayList<>());
        Processo processo2 = new Processo(null, "Preparar batata-palito saborizada com cebola", "Processo de preparação da saborização de patata-palito", ZonedDateTime.now().minusMonths(8).minusDays(5), "Roberto", new ArrayList<>());

        this.repositorioProcesso.deleteAll();
        this.repositorioProcesso.save(processo1);
        this.repositorioProcesso.save(processo2);

        ProdutoVenda prod1 = new ProdutoVenda(null, processo1, "A melhor batata-palha sabor bacon para seu strogonoff", "g", 10.0D, 10.0D);
        ProdutoVenda prod2 = new ProdutoVenda(null, processo2, "O melhor acompanhamento do seu hamburger", "kg", 10.0D, 10.0D);

        this.repositorioProdutoVenda.deleteAll();
        this.repositorioProdutoVenda.save(prod1);
        this.repositorioProdutoVenda.save(prod2);
    }

    @Test
    public void encontrarProdutoVenda() {
        List<Processo> ps1 = this.repositorioProcesso.findProcessoByNomeContaining("salgada");
        assertEquals(1, ps1.size());
        Processo p1 = ps1.get(0);
        assertNotNull(p1);
        ProdutoVenda pv1 = this.repositorioProdutoVenda.findByProcesso(p1).get();
        assertNotNull(pv1);
    }
}
