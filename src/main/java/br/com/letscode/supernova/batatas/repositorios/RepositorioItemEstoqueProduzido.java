package br.com.letscode.supernova.batatas.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.letscode.supernova.batatas.modelos.ExecucaoFaseProcessamento;
import br.com.letscode.supernova.batatas.modelos.ItemEstoqueInsumo;
import br.com.letscode.supernova.batatas.modelos.ItemEstoqueInsumoProduzido;

@Repository
public interface RepositorioItemEstoqueProduzido extends JpaRepository<ItemEstoqueInsumoProduzido, Long> {
    List<ItemEstoqueInsumoProduzido> findByItemEstoqueInsumo(ItemEstoqueInsumo itemEstoqueInsumo);
    List<ItemEstoqueInsumoProduzido> findByExecucaoFaseProcessamento(ExecucaoFaseProcessamento execucaoFaseProcessamento);
    List<ItemEstoqueInsumoProduzido> findByDataRegistroBetween(LocalDate inicio, LocalDate termino);
}
