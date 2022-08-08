package br.com.letscode.supernova.batatas.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class InsumoConsumidoFase {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Fase fase;
    @OneToOne
    private Insumo insumo;
    @Positive @NotNull
    private Double quantidadeConsumida;
    @NotNull @NotBlank @NotEmpty
    private String unidadeConsumo;

    public InsumoConsumidoFase() {
        
    }

    public InsumoConsumidoFase(Fase fase, Insumo insumo, @Positive @NotNull Double quantidadeConsumida,
            @NotNull @NotBlank @NotEmpty String unidadeConsumo) {
        this.fase = fase;
        this.insumo = insumo;
        this.quantidadeConsumida = quantidadeConsumida;
        this.unidadeConsumo = unidadeConsumo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Double getQuantidadeConsumida() {
        return quantidadeConsumida;
    }

    public void setQuantidadeConsumida(Double quantidadeConsumida) {
        this.quantidadeConsumida = quantidadeConsumida;
    }

    public String getUnidadeConsumo() {
        return unidadeConsumo;
    }

    public void setUnidadeConsumo(String unidadeConsumo) {
        this.unidadeConsumo = unidadeConsumo;
    }

    
}
