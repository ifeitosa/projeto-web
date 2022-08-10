package br.com.letscode.supernova.batatas.rest;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.supernova.batatas.dto.ProcessoDto;
import br.com.letscode.supernova.batatas.service.ProcessoService;

@RestController(value = "/processo")
public class ProcessoRestController {

    private ProcessoService service;
    
    public ProcessoRestController(@Autowired ProcessoService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<ProcessoDto> obterProcessos() {
        return this.service.obterProcessos();
    }

    @PostMapping("/")
    public ProcessoDto adicionarProceso(@RequestBody ProcessoDto dto) {
        return this.service.adicionarProcesso(dto);
    }

    @PutMapping("/{id:\\d+}")
    public ProcessoDto corrigirProcesso(@PathVariable Long id, @RequestBody ProcessoDto dto) {
        return this.service.corrigirProcesso(id, dto);
    }

    @GetMapping("/{id:\\d+}")
    public Optional<ProcessoDto> obterProcesso(@PathVariable Long id) {
        Objects.requireNonNull(id);
        return this.service.obterProcesso(id);
    }
    
}
