package br.com.letscode.supernova.batatas.modelos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import br.com.letscode.supernova.batatas.repositorios.RepositorioProcesso;

@DataJpaTest
public class ProcessoTest {

    private RepositorioProcesso repositorio;

    public ProcessoTest(@Autowired RepositorioProcesso repositorioProcesso) {
        this.repositorio = repositorioProcesso;
    }

    @BeforeEach
    public void registrarEntidades() {
        repositorio.deleteAll();
        final Processo[] processos = {
            new Processo(null, "Processo TESTE 1", "Descricação do processo", LocalDate.now().minusDays(5), "Agenor", new ArrayList<>()),
            new Processo(null, "Processo 2", "Descrição do processo TESTE", LocalDate.now().minusDays(2), "Cláudio", new ArrayList<>()),
            new Processo(null, "Processo 3", "Descrição TESTE do processo", LocalDate.now().minusDays(1), "Hugo", new ArrayList<>())
        };
        for(Processo p: processos) {
            repositorio.save(p);
        }
    }

    @Test
    public void encontrarProcessoComNomeContendoTeste() {
        List<Processo> processoTeste = repositorio.findProcessoByNomeContaining("TESTE");
        assertEquals(1, processoTeste.size());
    }

    @Test
    public void encontrarProcessoComDescricaoContendoTeste() {
        List<Processo> processoTeste = repositorio.findProcessoByDescricaoContaining("TESTE");
        assertEquals(2, processoTeste.size());
    }

    @Test
    public void encontrarProcessoComDataEntreDiasPrevistos() {
        List<Processo> processoTeste = repositorio.findProcessoByDataRegistroBetween(LocalDate.now().minusDays(3), LocalDate.now().minusDays(2));
        assertEquals(1, processoTeste.size());
    }
    
}
