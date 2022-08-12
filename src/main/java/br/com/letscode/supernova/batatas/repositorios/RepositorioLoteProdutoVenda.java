package br.com.letscode.supernova.batatas.repositorios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.letscode.supernova.batatas.modelos.LoteProdutoVenda;

@Repository
public interface RepositorioLoteProdutoVenda extends JpaRepository<LoteProdutoVenda, Long> {
    Optional<LoteProdutoVenda> findByLote(Long lote);
    List<LoteProdutoVenda> findByDataFabricacao(LocalDate data);
    List<LoteProdutoVenda> findByDataValidadeLessThanEqual(LocalDate data);
}
