package br.com.letscode.supernova.batatas.rest;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.letscode.supernova.batatas.dto.ProcessoDto;
import br.com.letscode.supernova.batatas.modelos.Processo;
import br.com.letscode.supernova.batatas.repositorios.RepositorioProcesso;

import br.com.letscode.supernova.batatas.mapper.*;

@RestController
@PermitAll
public class ProcessoRestController {

    private RepositorioProcesso repositorioProcesso;
    
    public ProcessoRestController(@Autowired RepositorioProcesso repositorioProcesso) {
        this.repositorioProcesso = repositorioProcesso;
    }
    @GetMapping("/")
    public List<ProcessoDto> obterProcessos() {
        return this.repositorioProcesso.findAll().stream().map(ProcessoMapper::fromEntity).collect(Collectors.toList());
    }

    @PostMapping("/")
    public Processo adicionarProceso(@RequestBody ProcessoDto dto) {
        return null;
        
    }
    
}
