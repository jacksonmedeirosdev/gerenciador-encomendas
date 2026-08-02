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
        String identificacaoNormalizada = identificacao.trim().toUpperCase();
        Bloco bloco = new Bloco(identificacaoNormalizada);

        if (repository.existsByIdentificacao(bloco.getIdentificacao())) {
            throw new BlocoJaExisteException(identificacaoNormalizada);
        }
        repository.save(bloco);
    }
}
