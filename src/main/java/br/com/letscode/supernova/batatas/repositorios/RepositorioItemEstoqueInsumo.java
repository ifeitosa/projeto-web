package br.com.letscode.supernova.batatas.repositorios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.letscode.supernova.batatas.modelos.Insumo;
import br.com.letscode.supernova.batatas.modelos.ItemEstoqueInsumo;

@Repository
public interface RepositorioItemEstoqueInsumo extends JpaRepository<ItemEstoqueInsumo, Long> {

    ItemEstoqueInsumo findByLote(Long lote);
    List<ItemEstoqueInsumo> findByInsumo(Insumo insumo);
    ItemEstoqueInsumo findByDataValidadeLessThan(LocalDate date);
    ItemEstoqueInsumo findByDataValidadeGreaterThan(LocalDate date);
    List<ItemEstoqueInsumo> findByQualidadeContaining(String qualidade);

    List<ItemEstoqueInsumo> findByQuantidadeIsGreaterThan(Double quantidade);

    List<ItemEstoqueInsumo> findByQuantidadeIsGreaterThanAndDataValidadeGreaterThanEqual(Double valor, LocalDate dataLimite);

    List<ItemEstoqueInsumo> findByQuantidadeIsGreaterThanAndDataValidadeIsGreaterThanEqual(Double valor, LocalDate dataLimite);

    @Query("SELECT i FROM ItemEstoqueInsumo i WHERE i.quantidade < :valor AND i.dataValidade < :dataLimite")
    List<ItemEstoqueInsumo> findByQuantidadeIsGreaterThanAndDataValidadeIsLessThanEqual(Double valor, LocalDate dataLimite);

    @Query("SELECT i FROM ItemEstoqueInsumo i WHERE i.quantidade < i.insumo.estoqueMinimo AND i.dataValidade >= :data")
    List<ItemEstoqueInsumo> encontrarItensSemEstoqueMinimo(LocalDate data);

    @Query("SELECT i FROM ItemEstoqueInsumo i WHERE i.quantidade > 0 AND i.dataValidade < :dataLimite")
    List<ItemEstoqueInsumo> encontrarItensComDataValidadeVencida(LocalDate dataLimite);
    
}
