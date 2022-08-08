package br.com.letscode.supernova.batatas.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.letscode.supernova.batatas.modelos.Processo;
import br.com.letscode.supernova.batatas.modelos.ProdutoVenda;

@Repository
public interface RepositorioProdutoVenda extends JpaRepository<ProdutoVenda, Long> {

    Optional<ProdutoVenda> findByProcesso(Processo processo);
    List<ProdutoVenda> findByDescricaoIgnoringCaseContaining(String parte);
    
}
