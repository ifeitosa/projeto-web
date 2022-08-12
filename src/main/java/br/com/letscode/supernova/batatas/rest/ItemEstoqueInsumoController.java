package br.com.letscode.supernova.batatas.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.supernova.batatas.dto.ItemEstoqueInsumoDto;
import br.com.letscode.supernova.batatas.service.ItemEstoqueInsumoService;

@RestController
@RequestMapping("/estoque")
public class ItemEstoqueInsumoController {
    
    @Autowired
    private ItemEstoqueInsumoService service;
    
    @GetMapping
    public List<ItemEstoqueInsumoDto> obterItensDoEstoque() {
        return service.obterItensDoEstoque();
    }
}
