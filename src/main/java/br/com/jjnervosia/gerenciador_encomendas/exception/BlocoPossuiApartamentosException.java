package br.com.jjnervosia.gerenciador_encomendas.exception;

public class BlocoPossuiApartamentosException extends RuntimeException {
    public BlocoPossuiApartamentosException(String identificacao) {
        super(
                "O bloco " + identificacao + " não pode ser removido porque possui apartamentos vinculados."
        );
    }
}
