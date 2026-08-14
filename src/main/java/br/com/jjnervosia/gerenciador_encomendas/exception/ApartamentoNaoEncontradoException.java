package br.com.jjnervosia.gerenciador_encomendas.exception;

public class ApartamentoNaoEncontradoException extends RuntimeException {
    public ApartamentoNaoEncontradoException(Long id) {
        super("Nenhum apartamento encontrado com o id " + id);
    }
}
