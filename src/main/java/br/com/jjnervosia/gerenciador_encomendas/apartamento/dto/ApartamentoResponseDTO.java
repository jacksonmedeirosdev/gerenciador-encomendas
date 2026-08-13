package br.com.jjnervosia.gerenciador_encomendas.apartamento.dto;

public record ApartamentoResponseDTO(
        Long id,
        String numero,
        Long blocoId,
        String identificacao
) {
}
