package br.com.jjnervosia.gerenciador_encomendas.exception;

public record BlocoError(
        String campo,
        String mensagem
) {
}
