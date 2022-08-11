package br.com.letscode.supernova.batatas.modelos;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "fases")
@ToString(exclude = "fases")
@Entity
public class Processo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @NotEmpty @NotBlank @Column(nullable = false, updatable = false)
    private String nome;
    @NotNull @NotEmpty @NotBlank @Column(nullable = false)
    private String descricao;
    @NotNull @Column(nullable = false, updatable = false)
    private ZonedDateTime dataRegistro = ZonedDateTime.now();
    @NotNull @NotEmpty @NotBlank @Column(nullable = false, updatable = false)
    private String responsavel;
    @ElementCollection(fetch = FetchType.EAGER) @CollectionTable(name = "fases_processo", joinColumns =  @JoinColumn(name = "processo", table = "FASE"))
    @OrderColumn(name = "sequencia")
    @Cascade(CascadeType.ALL)
    private List<Fase> fases = new ArrayList<>();
    
}
