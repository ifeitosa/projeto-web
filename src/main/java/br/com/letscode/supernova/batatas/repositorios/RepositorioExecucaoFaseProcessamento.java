package br.com.letscode.supernova.batatas.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.letscode.supernova.batatas.modelos.ExecucaoFaseProcessamento;
import br.com.letscode.supernova.batatas.modelos.Fase;

@Repository
public interface RepositorioExecucaoFaseProcessamento extends JpaRepository<ExecucaoFaseProcessamento, Long> {
    List<ExecucaoFaseProcessamento> findByFase(Fase fase);
}
