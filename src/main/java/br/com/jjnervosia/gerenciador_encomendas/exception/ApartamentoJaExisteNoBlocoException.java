package br.com.jjnervosia.gerenciador_encomendas.exception;


public class ApartamentoJaExisteNoBlocoException extends RuntimeException {
    public ApartamentoJaExisteNoBlocoException(String numero, String blocoIdentificacao) {
        super("O bloco " + blocoIdentificacao + " já possui o apartamento com o número " + numero + ".");
    }
}
