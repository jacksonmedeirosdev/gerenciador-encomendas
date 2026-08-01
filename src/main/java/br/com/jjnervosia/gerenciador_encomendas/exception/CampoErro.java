package br.com.jjnervosia.gerenciador_encomendas.exception;

public record CampoErro(
        String campo,
        String mensagem
) {
}
