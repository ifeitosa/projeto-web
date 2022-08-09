package br.com.letscode.supernova.batatas.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.letscode.supernova.batatas.modelos.Processo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessoDto {
    @NotNull @NotEmpty @NotBlank
    String nome;
    @NotNull @NotEmpty @NotBlank
    String descricao;
    @NotNull
    LocalDateTime dataRegistro = LocalDateTime.now();
    @NotNull @NotEmpty @NotBlank
    String responsavel;
    private List<FaseDto> fases = new ArrayList<>();

      
}
