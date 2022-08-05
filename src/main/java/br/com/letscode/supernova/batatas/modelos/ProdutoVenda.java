package br.com.letscode.supernova.batatas.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProdutoVenda {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ProdutoVenda() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    
}
