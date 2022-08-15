package br.com.letscode.supernova.batatas.errors;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.function.EntityResponse;

import br.com.letscode.supernova.batatas.dto.ErroDto;
import br.com.letscode.supernova.batatas.dto.ErrosDto;

@ControllerAdvice
public class BatatasErrorHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public EntityResponse<ErrosDto> entidadeNaoEncontrada(EntityNotFoundException ex) {
        ErrosDto erros = new ErrosDto();
        erros.setErros(List.of("Entidade não encontrada");
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
        // TODO Terminar tratamento da exceção
        return new ErrosDto();
    }

    
}
