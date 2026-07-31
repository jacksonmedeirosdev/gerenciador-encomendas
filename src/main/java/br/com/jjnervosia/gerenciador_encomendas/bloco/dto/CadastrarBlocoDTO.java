package br.com.jjnervosia.gerenciador_encomendas.bloco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CadastrarBlocoDTO(

        @NotBlank(message = "A identificação é de preenchimento obrigatório")
        @Size(max = 5, message = "A identificação deverá ter no máximo 5 caracteres")
        String identificacao
) {
}
