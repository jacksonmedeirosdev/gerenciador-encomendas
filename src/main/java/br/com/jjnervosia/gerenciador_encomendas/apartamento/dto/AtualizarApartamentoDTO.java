package br.com.jjnervosia.gerenciador_encomendas.apartamento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AtualizarApartamentoDTO(
        @NotBlank(message = "O número é de preenchimento obrigatório")
        @Size(max = 10, message = "O número deverá ter no máximo 10 caracteres")
        String numero,
        @NotNull(message = "O id do bloco é de preenchimento obrigatório")
        Long blocoId
) {
}
