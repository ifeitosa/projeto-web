package br.com.letscode.supernova.batatas.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.supernova.batatas.dto.InsumoDto;
import br.com.letscode.supernova.batatas.service.InsumoService;

@RestController
@RequestMapping("/insumo")
public class InsumoController {

    @Autowired
    private InsumoService service;
    
    @GetMapping("/{id:\\d+}")
    public Optional<InsumoDto> obterInsumo(@PathVariable Long id) {
        return this.service.obterInsumo(id);
    }

    @GetMapping
    public List<InsumoDto> obterInsumos() {
        return this.service.obterInsumos();
    }

    @PutMapping("/{id:\\d+}")
    public Optional<InsumoDto> corrigirInsumo(@PathVariable Long id, @RequestBody InsumoDto dto) {
        return this.service.corrigirInsumo(id, dto);
    }

    @PostMapping
    public Optional<InsumoDto> inserirInsumo(@RequestBody InsumoDto insumo) {
        return this.service.inserir(insumo);
    }
    
}
