package br.com.letscode.supernova.batatas.modelos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.letscode.supernova.batatas.repositorios.RepositorioInsumo;


@DataJpaTest
public class InsumoTest {
    
    private RepositorioInsumo repositorioInsumo;

    public InsumoTest(@Autowired RepositorioInsumo repositorioInsumo) {
        this.repositorioInsumo = repositorioInsumo;
    }

    @BeforeEach
    @Transactional
    public void inserirInsumosDeTeste() {
        this.repositorioInsumo.deleteAll();
        Insumo insumo1 = new Insumo(null, "Batata-palha", "kg", 10D);
        Insumo insumo2 = new Insumo(null, "Batata-palito", "kg", 10.0D);
        Insumo insumo3 = new Insumo(null, "Batata cozida", "kg", 10.0D);
        Insumo insumo4 = new Insumo(null, "Purê de batata", "kg", 10.0D);

        this.repositorioInsumo.save(insumo1);
        this.repositorioInsumo.save(insumo2);
        this.repositorioInsumo.save(insumo3);
        this.repositorioInsumo.save(insumo4);
    }

    @Test
    public void encontrarInsumosDeTeste() {
        List<Insumo> insumosTeste = this.repositorioInsumo.findByNomeIgnoringCaseLike("batata-palha");
        List<Insumo> insumosTeste2 = this.repositorioInsumo.findByNomeIgnoringCaseContaining("batata");
        List<Insumo> insumosCompostos = this.repositorioInsumo.findByNomeIgnoringCaseContaining("-");

        assertEquals(1, insumosTeste.size());
        assertEquals(4, insumosTeste2.size());
        assertEquals(2, insumosCompostos.size());
    }
}
