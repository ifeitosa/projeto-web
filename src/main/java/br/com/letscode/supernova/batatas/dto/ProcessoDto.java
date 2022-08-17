package br.com.letscode.supernova.batatas.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
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
@EqualsAndHashCode //(exclude = "dataRegistro")
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoDto {
    @Positive(message = "O número identificador do processo deve ser positivo")
    private Long id;
    @NotNull(message = "O processo deve ser identificado por um nome válido") 
    @NotEmpty(message = "O processo deve ser identificado por um nome válido") 
    @NotBlank(message = "O processo deve ser identificado por um nome válido")
    private String nome;
    @NotNull(message = "O processo deve possuir uma descrição válida")
    @NotEmpty(message = "O processo deve possuir uma descrição válida")
    @NotBlank(message = "O processo deve possuir uma descrição válida")
    private String descricao;
    @NotNull(message = "A data de registro deve ser fornecida")
    @PastOrPresent(message = "A data de registro deve ser no máximo atual")
    @JsonFormat(lenient = OptBoolean.TRUE)
    private LocalDate dataRegistro;
    @NotNull(message = "O nome do responsável deve ser fornecido")
    @NotEmpty(message = "O nome do responsável deve ser fornecido")
    @NotBlank(message = "O nome do responsável deve ser fornecido")
    @Pattern(regexp = "[A-Za-z ]+", message = "O nome do responsável deve ser fornecido")
    private String responsavel;
    @NotEmpty(message = "A lista de fases do processo deve ser fornecida")
    @NotNull(message  = "A lista de fases do processo deve ser fornecida")
    @Valid
    private List<FaseDto> fases = new ArrayList<>();

      
}
