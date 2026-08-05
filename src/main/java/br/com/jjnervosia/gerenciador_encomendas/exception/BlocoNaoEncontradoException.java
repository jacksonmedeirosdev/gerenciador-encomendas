package br.com.jjnervosia.gerenciador_encomendas.exception;

public class BlocoNaoEncontradoException extends RuntimeException {
    public BlocoNaoEncontradoException(Long id) {

        super("Nenhum bloco encontrado para o id " + id);
    }
}
