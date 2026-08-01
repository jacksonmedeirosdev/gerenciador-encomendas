package br.com.jjnervosia.gerenciador_encomendas.bloco;

import br.com.jjnervosia.gerenciador_encomendas.exception.BlocoJaExisteException;
import org.springframework.stereotype.Service;

@Service
public class BlocoService {


    private final BlocoRepository repository;

    public BlocoService(BlocoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(String identificacao){
        Bloco bloco = new Bloco(identificacao);
        if (repository.existsByIdentificacao(bloco.getIdentificacao())) {
            throw new BlocoJaExisteException(identificacao);
        }
        repository.save(bloco);
    }
}
