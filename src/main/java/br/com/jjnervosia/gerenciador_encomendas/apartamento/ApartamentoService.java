package br.com.jjnervosia.gerenciador_encomendas.apartamento;

import br.com.jjnervosia.gerenciador_encomendas.apartamento.dto.ApartamentoResponseDTO;
import br.com.jjnervosia.gerenciador_encomendas.bloco.Bloco;
import br.com.jjnervosia.gerenciador_encomendas.bloco.BlocoRepository;
import br.com.jjnervosia.gerenciador_encomendas.exception.ApartamentoJaExisteNoBlocoException;
import br.com.jjnervosia.gerenciador_encomendas.exception.ApartamentoNaoEncontradoException;
import br.com.jjnervosia.gerenciador_encomendas.exception.BlocoNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<ApartamentoResponseDTO> listar(){
        List<Apartamento> apartamentos = apartamentoRepository.findAll();
        List<ApartamentoResponseDTO> apartamentosResponseDTO = new ArrayList<>();

        for (Apartamento apartamento : apartamentos){
            apartamentosResponseDTO.add(new ApartamentoResponseDTO(
                    apartamento.getId(),
                    apartamento.getNumero(),
                    apartamento.getBloco().getId(),
                    apartamento.getBloco().getIdentificacao()));

        }
        return apartamentosResponseDTO;
    }

    public ApartamentoResponseDTO buscarPorId(Long id){
        Apartamento apartamento = apartamentoRepository.findById(id).orElseThrow(()-> new ApartamentoNaoEncontradoException(id));
        return new ApartamentoResponseDTO(
                apartamento.getId(),
                apartamento.getNumero(),
                apartamento.getBloco().getId(),
                apartamento.getBloco().getIdentificacao()
        );
    }

}
