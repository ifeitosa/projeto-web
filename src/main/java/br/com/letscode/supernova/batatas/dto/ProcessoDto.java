package br.com.letscode.supernova.batatas.dto;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JacksonInject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoDto {
    @NotNull @Positive
    private Long id;
    @NotNull @NotEmpty @NotBlank
    private String nome;
    @NotNull @NotEmpty @NotBlank
    private String descricao;
    @NotNull 
    private ZonedDateTime dataRegistro;
    @NotNull @NotEmpty @NotBlank
    private String responsavel;
    private List<FaseDto> fases = new ArrayList<>();

      
}
