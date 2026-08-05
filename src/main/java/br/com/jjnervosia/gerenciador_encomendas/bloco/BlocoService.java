package br.com.jjnervosia.gerenciador_encomendas.bloco;

import br.com.jjnervosia.gerenciador_encomendas.bloco.dto.BlocoResponseDTO;
import br.com.jjnervosia.gerenciador_encomendas.exception.BlocoJaExisteException;
import br.com.jjnervosia.gerenciador_encomendas.exception.BlocoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlocoService {


    private final BlocoRepository repository;

    public BlocoService(BlocoRepository repository) {
        this.repository = repository;
    }

    public void cadastrar(String identificacao) {
        String identificacaoNormalizada = identificacao.trim().toUpperCase();
        Bloco bloco = new Bloco(identificacaoNormalizada);

        if (repository.existsByIdentificacao(bloco.getIdentificacao())) {
            throw new BlocoJaExisteException(identificacaoNormalizada);
        }
        repository.save(bloco);
    }

    public List<BlocoResponseDTO> listar() {
        List<BlocoResponseDTO> blocosResponse = new ArrayList<>();

        for (Bloco bloco : repository.findAll()) {
            blocosResponse.add(
                    new BlocoResponseDTO(
                            bloco.getId(),
                            bloco.getIdentificacao()
                    )
            );
        }

        return blocosResponse;
    }
    public BlocoResponseDTO buscarPorId(Long id){

        Bloco bloco = repository.findById(id).orElseThrow(() ->  new BlocoNaoEncontradoException(id));

        return new BlocoResponseDTO(
                bloco.getId(),
                bloco.getIdentificacao());

    }
}
