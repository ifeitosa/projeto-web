package br.com.letscode.supernova.batatas.modelos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.letscode.supernova.batatas.repositorios.RepositorioProcesso;

@SpringBootTest
public class ProcessoTest {

    @Autowired
    private RepositorioProcesso repositorio;

    @BeforeEach
    public void registrarEntidades() {
        repositorio.deleteAll();
        final Processo[] processos = {
            new Processo("Processo TESTE 1", "Descricação do processo", LocalDateTime.now().minusDays(5), "Agenor"),
            new Processo("Processo 2", "Descrição do processo TESTE", LocalDateTime.now().minusDays(2), "Cláudio"),
            new Processo("Processo 3", "Descrição TESTE do processo", LocalDateTime.now().minusDays(1), "Hugo")
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
        List<Processo> processoTeste = repositorio.findProcessoByDataRegistroBetween(LocalDateTime.now().minusDays(3), LocalDateTime.now().minusDays(2));
        assertEquals(1, processoTeste.size());
    }
    
}
