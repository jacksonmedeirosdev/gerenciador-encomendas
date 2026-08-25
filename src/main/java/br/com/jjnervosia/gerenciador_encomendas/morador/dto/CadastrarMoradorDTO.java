package br.com.jjnervosia.gerenciador_encomendas.morador.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CadastrarMoradorDTO(

        @NotBlank(message = "O nome do morador é obrigatório")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @NotNull(message = "O id do apartamento é de preenchimento obrigatório")
        Long apartamentoId
) {
}
