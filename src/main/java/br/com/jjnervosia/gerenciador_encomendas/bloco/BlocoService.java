package br.com.jjnervosia.gerenciador_encomendas.bloco;

import br.com.jjnervosia.gerenciador_encomendas.bloco.dto.AtualizarBlocoDTO;
import br.com.jjnervosia.gerenciador_encomendas.bloco.dto.BlocoResponseDTO;
import br.com.jjnervosia.gerenciador_encomendas.exception.BlocoJaExisteException;
import br.com.jjnervosia.gerenciador_encomendas.exception.BlocoNaoEncontradoException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public BlocoResponseDTO atualizar(AtualizarBlocoDTO dto, Long id) {

        Bloco blocoAtual = repository.findById(id).orElseThrow(() ->  new BlocoNaoEncontradoException(id));

        Optional<Bloco> blocoEncontrado = repository.findByIdentificacao(dto.identificacao());

        if (blocoEncontrado.isPresent()) {
            if (!blocoEncontrado.get().getId().equals(blocoAtual.getId())) {
                throw new BlocoJaExisteException(dto.identificacao());
            }
        }
        blocoAtual.alterarIdentificacao(dto.identificacao());

        repository.save(blocoAtual);

        return new BlocoResponseDTO(
                blocoAtual.getId(),
                blocoAtual.getIdentificacao()
        );


    }
}
