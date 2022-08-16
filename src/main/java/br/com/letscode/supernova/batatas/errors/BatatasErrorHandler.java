package br.com.letscode.supernova.batatas.errors;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.function.EntityResponse;

import br.com.letscode.supernova.batatas.dto.ErrosDto;

@ControllerAdvice
public class BatatasErrorHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public EntityResponse<ErrosDto> entidadeNaoEncontrada(EntityNotFoundException ex) {
        ErrosDto erros = new ErrosDto();
        erros.setErros(List.of("Entidade não encontrada"));
        return EntityResponse.fromObject(erros).status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public EntityResponse<ErrosDto> erroValidacao(MethodArgumentNotValidException ex) {
        List<String> mensagens = new ArrayList<>();        
        ex.getBindingResult().getAllErrors().forEach(erro -> {
            String mensagem = erro.getDefaultMessage();
            mensagens.add(mensagem);
        });
        return EntityResponse.fromObject(new ErrosDto(mensagens)).status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public EntityResponse<ErrosDto> violacaoDeRestricao(ConstraintViolationException ex) {
        ErrosDto dto = new ErrosDto(new ArrayList<>());
        ex.getConstraintViolations().stream().forEach(cv -> {
            String caminho = cv.getPropertyPath().toString();
            caminho = caminho.concat(": " + cv.getMessage());
            dto.getErros().add(caminho);
        });
        return EntityResponse.fromObject(new ErrosDto()).build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public EntityResponse<ErrosDto> elementoNaoEncontrado(NoSuchElementException ex) {
        return EntityResponse.fromObject(new ErrosDto(List.of(ex.getMessage(), ex.getCause().getMessage()))).build();
    }

    
}
