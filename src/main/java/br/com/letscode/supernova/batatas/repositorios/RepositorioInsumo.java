package br.com.letscode.supernova.batatas.repositorios;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.letscode.supernova.batatas.modelos.Insumo;

@Repository
public interface RepositorioInsumo extends JpaRepository<Insumo, Long> {
    List<Insumo> findByNomeIgnoringCaseContaining(@NotNull @NotBlank @NotEmpty String parteNome);
    List<Insumo> findByNomeIgnoringCaseLike(@NotNull @NotBlank @NotEmpty String nome);
}
