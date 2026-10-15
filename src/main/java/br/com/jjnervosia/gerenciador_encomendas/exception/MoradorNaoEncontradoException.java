package br.com.jjnervosia.gerenciador_encomendas.exception;

public class MoradorNaoEncontradoException extends RuntimeException {
    public MoradorNaoEncontradoException(Long id) {
        super("Nenhum morador encontrado com o id " + id);
    }
}
