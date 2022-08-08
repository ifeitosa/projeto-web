package br.com.letscode.supernova.batatas.modelos;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.LazyToOne;

import br.com.letscode.supernova.batatas.dto.ProcessoDto;

@Entity
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @NotEmpty @NotBlank
    private String nome;
    @NotNull @NotEmpty @NotBlank
    private String descricao;
    @NotNull
    private LocalDateTime dataRegistro = LocalDateTime.now();
    @NotNull @NotEmpty @NotBlank
    private String responsavel;

    public Processo() {
    }

    public Processo(@NotNull @NotEmpty @NotBlank String nome, @NotNull @NotEmpty @NotBlank String descricao,
            @NotNull LocalDateTime dataRegistro, @NotNull @NotEmpty @NotBlank String responsavel) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataRegistro = dataRegistro;
        this.responsavel = responsavel;
    }

    public Processo(ProcessoDto dto) {
        this(dto.getNome(), dto.getDescricao(), dto.getDataRegistro(), dto.getResponsavel());
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public String getResponsavel() {
        return responsavel;
    }

    @Override
    public String toString() {
        return String.format("Processo[id=%d, nome=%s, descricao=%s, data=%s, responsavel=%s]",
                this.id, this.nome, this.descricao, this.dataRegistro.toString(), this.responsavel);
    }

}
