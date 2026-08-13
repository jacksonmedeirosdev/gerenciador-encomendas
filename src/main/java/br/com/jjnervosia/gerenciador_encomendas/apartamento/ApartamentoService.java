package br.com.jjnervosia.gerenciador_encomendas.apartamento;

import br.com.jjnervosia.gerenciador_encomendas.apartamento.dto.ApartamentoResponseDTO;
import br.com.jjnervosia.gerenciador_encomendas.bloco.Bloco;
import br.com.jjnervosia.gerenciador_encomendas.bloco.BlocoRepository;
import br.com.jjnervosia.gerenciador_encomendas.exception.ApartamentoJaExisteNoBlocoException;
import br.com.jjnervosia.gerenciador_encomendas.exception.BlocoNaoEncontradoException;
import org.springframework.stereotype.Service;

@Service
public class ApartamentoService {

    private final ApartamentoRepository apartamentoRepository;
    private final BlocoRepository blocoRepository;

    public ApartamentoService(
            ApartamentoRepository apartamentoRepository,
            BlocoRepository blocoRepository
    ){
        this.apartamentoRepository = apartamentoRepository;
        this.blocoRepository = blocoRepository;
    }

    public ApartamentoResponseDTO cadastrar(String numero, Long blocoId){
        Bloco bloco = blocoRepository.findById(blocoId).orElseThrow(()-> new BlocoNaoEncontradoException(blocoId));

        if (apartamentoRepository.existsByNumeroAndBlocoId(numero, blocoId)){
            throw new ApartamentoJaExisteNoBlocoException(numero, bloco.getIdentificacao());
        }

        Apartamento apartamento = new Apartamento(numero, bloco);

        //deixar explicito que o retorno é o apartamento persistido no banco
        Apartamento apartamentoSalvo = apartamentoRepository.save(apartamento);

        return new ApartamentoResponseDTO(
                apartamentoSalvo.getId(),
                apartamentoSalvo.getNumero(),
                bloco.getId(),
                bloco.getIdentificacao()

        );
    }

}
