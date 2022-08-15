package br.com.letscode.supernova.batatas.errors;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.function.EntityResponse;

import br.com.letscode.supernova.batatas.dto.ErroDto;
import br.com.letscode.supernova.batatas.dto.ErrosDto;

@Component
public class BatatasErrorHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public EntityResponse<ErrosDto> entidadeNaoEncontrada(EntityNotFoundException ex) {
        ErrosDto erros = new ErrosDto();
        erros.setErros(List.of(new ErroDto(ex.getLocalizedMessage())));
        return EntityResponse.fromObject(erros).status(HttpStatus.NOT_FOUND).build();
    }

    
}
