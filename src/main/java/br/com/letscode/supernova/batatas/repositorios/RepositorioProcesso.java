package br.com.letscode.supernova.batatas.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.letscode.supernova.batatas.modelos.Processo;

@Repository
public interface RepositorioProcesso extends JpaRepository<Processo, Long> {

    List<Processo> findProcessoByNomeContaining(String parte);
    List<Processo> findProcessoByDescricaoContaining(String parte);
    List<Processo> findProcessoByDataRegistroBetween(LocalDate dataInicio, LocalDate dataFinal);
    
}
