package br.com.letscode.supernova.batatas.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.letscode.supernova.batatas.modelos.ExecucaoFaseProcessamento;
import br.com.letscode.supernova.batatas.modelos.Fase;

@Repository
public interface RepositorioExecucaoFaseProcessamento extends JpaRepository<ExecucaoFaseProcessamento, Long> {
    List<ExecucaoFaseProcessamento> findByFase(Fase fase);


    @Query("SELECT e FROM ExecucaoFaseProcessamento e WHERE e.fase.id = :idFase")
    List<ExecucaoFaseProcessamento> findByIdFase(Long idFase);

    @Query("SELECT e FROM ExecucaoFaseProcessamento e WHERE (:inicio <= e.dataInicio AND e.dataInicio <= :termino) OR (:inicio <= e.dataTermino AND e.dataTermino <= :termino)")
    List<ExecucaoFaseProcessamento> encontrarTodosQueIniciaramOuTerminaramEntre(LocalDate inicio, LocalDate termino);
}
