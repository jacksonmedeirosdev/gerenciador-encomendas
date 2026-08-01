package br.com.jjnervosia.gerenciador_encomendas.exception;


public class BlocoJaExisteException extends RuntimeException {
    public BlocoJaExisteException(String identificacao) {
        super("Já existe um bloco com a identificação: " + identificacao);
    }
}
