package br.com.letscode.supernova.batatas.modelos;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
@EqualsAndHashCode
@ToString
@Entity
public class ExecucaoFaseProcessamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OS;

    @ManyToOne(targetEntity = Fase.class)
    private Fase fase;

    private LocalDate dataInicio;
    private LocalDate dataTermino;
}
