package br.com.jjnervosia.gerenciador_encomendas.morador;


import br.com.jjnervosia.gerenciador_encomendas.apartamento.Apartamento;
import br.com.jjnervosia.gerenciador_encomendas.apartamento.ApartamentoRepository;
import br.com.jjnervosia.gerenciador_encomendas.exception.ApartamentoNaoEncontradoException;
import br.com.jjnervosia.gerenciador_encomendas.morador.dto.MoradorResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class MoradorService {

    private final  MoradorRepository moradorRepository;
    private final ApartamentoRepository apartamentoRepository;

    public MoradorService(MoradorRepository moradorRepository, ApartamentoRepository apartamentoRepository) {
        this.moradorRepository = moradorRepository;
        this.apartamentoRepository = apartamentoRepository;
    }

    public MoradorResponseDTO cadastrar(String nome, Long apartamentoId){
        Apartamento apartamento = apartamentoRepository.findById(apartamentoId).orElseThrow(()-> new ApartamentoNaoEncontradoException(apartamentoId));
        Morador morador = new Morador(nome, apartamento);

        Morador moradorSalvo = moradorRepository.save(morador);

        return new MoradorResponseDTO(moradorSalvo.getId(), moradorSalvo.getNome(), apartamento.getId(), apartamento.getNumero());


    }
}
