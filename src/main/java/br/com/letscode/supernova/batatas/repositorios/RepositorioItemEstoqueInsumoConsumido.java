package br.com.letscode.supernova.batatas.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.letscode.supernova.batatas.modelos.ExecucaoFaseProcessamento;
import br.com.letscode.supernova.batatas.modelos.ItemEstoqueInsumo;
import br.com.letscode.supernova.batatas.modelos.ItemEstoqueInsumoConsumido;

public interface RepositorioItemEstoqueInsumoConsumido extends JpaRepository<ItemEstoqueInsumoConsumido, Long> {
    List<ItemEstoqueInsumoConsumido> findByItemEstoqueInsumo(ItemEstoqueInsumo itemEstoqueInsumo);
    List<ItemEstoqueInsumoConsumido> findByExecucaoFaseProcessamento(ExecucaoFaseProcessamento execucaoFaseProcessamento);
    List<ItemEstoqueInsumoConsumido> findByDataRegistroBetween(LocalDate inicio, LocalDate termino);
}
