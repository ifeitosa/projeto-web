package br.com.letscode.supernova.batatas.rest;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.letscode.supernova.batatas.dto.ProcessoDto;
import br.com.letscode.supernova.batatas.service.ProcessoService;

@RestController
@RequestMapping(path = "/processo")
public class ProcessoRestController {

    private ProcessoService service;
    private ObjectMapper objectMapper;
    
    public ProcessoRestController(@Autowired ProcessoService service, ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<ProcessoDto> obterProcessos() {
        return this.service.obterProcessos();
    }

    @PostMapping
    public ProcessoDto adicionarProceso(@RequestBody ProcessoDto dto) throws JsonProcessingException {
        System.out.println(this.objectMapper.writeValueAsString(dto));
        ProcessoDto rdto = this.service.adicionarProcesso(dto);
        System.out.println(this.objectMapper.writeValueAsString(rdto));
        return rdto;
    }

    @PutMapping(path = "/{id:\\d+}")
    public ProcessoDto corrigirProcesso(@PathVariable Long id, @RequestBody ProcessoDto dto) {
        return this.service.corrigirProcesso(id, dto);
    }

    @GetMapping(path = "/{id:\\d+}")
    public Optional<ProcessoDto> obterProcesso(@PathVariable Long id) {
        Objects.requireNonNull(id);
        return this.service.obterProcesso(id);
    }

    @DeleteMapping("/{id:\\d+}")
    public void deletarProcesso(@PathVariable Long id) {
        this.service.deletarProcesso(id);
    }
    
}
