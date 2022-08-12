package br.com.letscode.supernova.batatas.dto;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoDto {
    @Positive
    private Long id;
    @NotNull @NotEmpty @NotBlank
    private String nome;
    @NotNull @NotEmpty @NotBlank
    private String descricao;
    @NotNull @JsonFormat(lenient = OptBoolean.TRUE)
    private ZonedDateTime dataRegistro;
    @NotNull @NotEmpty @NotBlank
    private String responsavel;
    private List<FaseDto> fases = new ArrayList<>();

      
}
