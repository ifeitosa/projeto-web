package br.com.letscode.supernova.batatas.repositorios;

import java.time.ZonedDateTime;
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
    ItemEstoqueInsumo findByDataValidadeLessThan(ZonedDateTime date);
    ItemEstoqueInsumo findByDataValidadeGreaterThan(ZonedDateTime date);
    List<ItemEstoqueInsumo> findByQualidadeContaining(String qualidade);

    List<ItemEstoqueInsumo> findByQuantidadeIsGreaterThan(Double quantidade);
}
