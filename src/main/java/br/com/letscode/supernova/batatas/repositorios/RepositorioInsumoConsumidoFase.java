package br.com.letscode.supernova.batatas.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.letscode.supernova.batatas.modelos.InsumoConsumidoFase;

@Repository
public interface RepositorioInsumoConsumidoFase extends JpaRepository<InsumoConsumidoFase, Long> {
    
}
