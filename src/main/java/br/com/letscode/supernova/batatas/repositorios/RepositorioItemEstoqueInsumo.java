package br.com.letscode.supernova.batatas.repositorios;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import br.com.letscode.supernova.batatas.modelos.Insumo;
import br.com.letscode.supernova.batatas.modelos.ItemEstoqueInsumo;

@Repository
public interface RepositorioItemEstoqueInsumo extends JpaRepository<ItemEstoqueInsumo, Long> {

    ItemEstoqueInsumo findByLote(Long lote);
    List<ItemEstoqueInsumo> findByInsumo(Insumo insumo);
    ItemEstoqueInsumo findByDataValidadeLessThan(ZonedDateTime date);
    ItemEstoqueInsumo findByDataValidadeGreaterThan(ZonedDateTime date);
    List<ItemEstoqueInsumo> findByQualidadeContaining(String qualidade);

    List<ItemEstoqueInsumo> findByQuantidadeIsGreaterThan(Double quantidade);

    List<ItemEstoqueInsumo> findByQuantidadeIsGreaterThanAndDataValidadeGreaterThanEqual(Double valor, ZonedDateTime dataLimite);

    List<ItemEstoqueInsumo> findByQuantidadeIsGreaterThanAndDataValidadeIsGreaterThanEqual(Double valor, ZonedDateTime dataLimite);

    @Query("SELECT i FROM ItemEstoqueInsumo i WHERE i.quantidade < :valor AND i.dataValidade < :dataLimite")
    List<ItemEstoqueInsumo> findByQuantidadeIsGreaterThanAndDataValidadeIsLessThanEqual(Double valor, ZonedDateTime dataLimite);

    @Query("SELECT i FROM ItemEstoqueInsumo i WHERE i.quantidade < i.insumo.estoqueMinimo AND i.dataValidade >= :data")
    List<ItemEstoqueInsumo> encontrarItensSemEstoqueMinimo(ZonedDateTime data);

    @Query("SELECT i FROM ItemEstoqueInsumo i WHERE i.quantidade > 0 AND i.dataValidade < :dataLimite")
    List<ItemEstoqueInsumo> encontrarItensComDataValidadeVencida(ZonedDateTime dataLimite);
    
}
