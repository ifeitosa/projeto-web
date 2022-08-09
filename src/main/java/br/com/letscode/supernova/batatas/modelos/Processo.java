package br.com.letscode.supernova.batatas.modelos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.letscode.supernova.batatas.dto.ProcessoDto;
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
@ToString
@Entity
public class Processo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull @NotEmpty @NotBlank @Column(nullable = false, updatable = false)
    private String nome;
    @NotNull @NotEmpty @NotBlank @Column(nullable = false)
    private String descricao;
    @NotNull @Column(nullable = false, updatable = false)
    private LocalDateTime dataRegistro = LocalDateTime.now();
    @NotNull @NotEmpty @NotBlank @Column(nullable = false, updatable = false)
    private String responsavel;
    @ElementCollection @CollectionTable(name = "fases_processo", joinColumns =  @JoinColumn(name = "processo", table = "FASE"))
    @OrderColumn(name = "sequencia")
    private List<Fase> fases = new ArrayList<>();
    
    public Processo(ProcessoDto dto) {
        this(null, dto.getNome(), dto.getDescricao(), dto.getDataRegistro(), dto.getResponsavel(),
            dto.getFases().stream().map(fase -> new Fase(FaseDto, this)).collect(Collectors.toList()));
    }
}
