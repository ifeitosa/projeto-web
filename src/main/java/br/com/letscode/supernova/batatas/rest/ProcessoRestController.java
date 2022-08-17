package br.com.letscode.supernova.batatas.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import br.com.letscode.supernova.batatas.dto.ProcessoDto;
import br.com.letscode.supernova.batatas.service.ProcessoService;

@RestController
@RequestMapping(path = "/processo")
public class ProcessoRestController {

    @Autowired
    private ProcessoService service;

    @Cacheable(cacheNames = "obterProcessos")
    @GetMapping
    public List<ProcessoDto> obterProcessos() {
        return this.service.obterProcessos();
    }

    @PostMapping
    public ProcessoDto adicionarProceso(@Valid @RequestBody ProcessoDto dto) {
        return this.service.adicionarProcesso(dto);
    }

    @CacheEvict(cacheNames = "obterProcessos")
    @PutMapping(path = "/{id:\\d+}")
    public ResponseEntity<ProcessoDto> corrigirProcesso(@PathVariable Long id, @Valid @RequestBody ProcessoDto dto) {
        ProcessoDto retorno = this.service.corrigirProcesso(id, dto);
        if (retorno == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.of(Optional.of(retorno));
        }
    }

    @Cacheable(cacheNames = "obterProcessos")
    @GetMapping(path = "/{id:\\d+}")
    public ResponseEntity<ProcessoDto> obterProcesso(@PathVariable Long id) {
        Objects.requireNonNull(id);
        Optional<ProcessoDto> dto = this.service.obterProcesso(id);
        if (dto.isPresent()) {
            return ResponseEntity.of(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @CacheEvict(cacheNames = "obterProcessos")
    @DeleteMapping("/{id:\\d+}")
    public void deletarProcesso(@PathVariable Long id) {
        this.service.deletarProcesso(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> tratarExcecaoValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(erro -> {
            String campo = ((FieldError) erro).getField();
            String mensagem = erro.getDefaultMessage();
            erros.put(campo, mensagem);
        });
        return erros;
    }

}
