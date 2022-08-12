package br.com.letscode.supernova.batatas.rest;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.supernova.batatas.dto.ItemEstoqueInsumoDto;
import br.com.letscode.supernova.batatas.service.ItemEstoqueInsumoService;

@RestController
@RequestMapping("/estoqueInsumo")
public class ItemEstoqueInsumoController {
    
    @Autowired
    private ItemEstoqueInsumoService service;
    
    @GetMapping
    public List<ItemEstoqueInsumoDto> obterItensDoEstoque() {
        return service.obterItensDoEstoque();
    }

    @GetMapping("/ativos")
    public List<ItemEstoqueInsumoDto> obterItensDoEstoqueAtivos() {
        return service.obterItensDoEstoqueAtivos();
    }

    @GetMapping("/vencidos")
    public List<ItemEstoqueInsumoDto> obterItensDoEstoqueVencidos() {
        return service.obterItensDoEstoqueVencidos();
    }

    @GetMapping("/{id:\\d+}")
    public Optional<ItemEstoqueInsumoDto> obterItemDoEstoque(@PathVariable Long id){
        return service.obterItemDoEstoque(id);
    }

    @GetMapping("/semEstoqueMinimo")
    public List<ItemEstoqueInsumoDto> obterItensSemEstoqueMinimo() {
        return service.obterItensSemEstoqueMinimo();
    }
    

    @PutMapping("/{id:\\d+}")
    public Optional<ItemEstoqueInsumoDto> corrigirItemEstoqueInsumo(@Valid @PathVariable Long id, @RequestBody ItemEstoqueInsumoDto dto) {
        return this.service.corrigirItemEstoqueInsumo(id, dto);
    }

    @PostMapping
    public Optional<ItemEstoqueInsumoDto> inserirItemEstoqueInsumo(@Valid @RequestBody ItemEstoqueInsumoDto dto) {
        return this.service.inserirItemEstoqueInsumo(dto);
    }
}
