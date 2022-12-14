package br.com.letscode.supernova.batatas.dto;

import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class IdFaseDto {
    @Positive(message = "O número de identificação da fase deve ser positivo")
    private Long id;
}
