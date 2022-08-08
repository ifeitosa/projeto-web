package br.com.letscode.supernova.batatas.modelos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Fase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @NotNull
    private Processo processo;

    @OneToMany
    private List<InsumoConsumidoFase> insumosConsumidoFase;

    @Positive @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer sequencia;
    @NotNull @NotEmpty @NotBlank
    private String nome;
    @NotNull @NotBlank @NotEmpty
    private String instrucoes;
    @NotNull @NotBlank @NotEmpty
    private String unidadeProducao;
    @Positive @NotNull
    private Double quantidadeProduzida;

    public Fase() { }

    public Fase(@NotNull Processo processo, @Positive Integer sequencia, @NotNull @NotEmpty @NotBlank String nome,
            @NotNull @NotBlank @NotEmpty String instrucoes, @NotNull @NotBlank @NotEmpty String unidadeProducao,
            @Positive @NotNull Double quantidadeProduzida) {
        this.processo = processo;
        this.sequencia = sequencia;
        this.nome = nome;
        this.instrucoes = instrucoes;
        this.unidadeProducao = unidadeProducao;
        this.quantidadeProduzida = quantidadeProduzida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(Integer sequencia) {
        this.sequencia = sequencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(String instrucoes) {
        this.instrucoes = instrucoes;
    }

    public String getUnidadeProducao() {
        return unidadeProducao;
    }

    public void setUnidadeProducao(String unidadeProducao) {
        this.unidadeProducao = unidadeProducao;
    }

    public Double getQuantidadeProduzida() {
        return quantidadeProduzida;
    }

    public void setQuantidadeProduzida(Double quantidadeProduzida) {
        this.quantidadeProduzida = quantidadeProduzida;
    }

    public List<InsumoConsumidoFase> getInsumosConsumidoFases() {
        return insumosConsumidoFase;
    }

    public void setInsumosConsumidoFases(List<InsumoConsumidoFase> insumosConsumidoFases) {
        this.insumosConsumidoFase = insumosConsumidoFases;
    }

    
}
