package br.com.letscode.supernova.batatas.repositorios;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.letscode.supernova.batatas.modelos.ExecucaoFaseProcessamento;
import br.com.letscode.supernova.batatas.modelos.ItemProduzidoExecucao;
import br.com.letscode.supernova.batatas.modelos.ProdutoVenda;

@Repository
public interface RepositorioItemProduzidoExecucao extends JpaRepository<ItemProduzidoExecucao, Long> {
    List<ItemProduzidoExecucao> findByExecucaoFaseProcessamento(ExecucaoFaseProcessamento execucaoFaseProcessamento);
    Optional<ItemProduzidoExecucao> findByLoteProdutoVenda(ProdutoVenda produtoVenda);
    List<ItemProduzidoExecucao> findByDataRegistroBetween(LocalDate inicio, LocalDate termino);
    
}
