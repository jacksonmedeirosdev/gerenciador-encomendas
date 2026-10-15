package br.com.jjnervosia.gerenciador_encomendas.morador;


import br.com.jjnervosia.gerenciador_encomendas.apartamento.Apartamento;
import br.com.jjnervosia.gerenciador_encomendas.apartamento.ApartamentoRepository;
import br.com.jjnervosia.gerenciador_encomendas.exception.ApartamentoNaoEncontradoException;
import br.com.jjnervosia.gerenciador_encomendas.exception.MoradorNaoEncontradoException;
import br.com.jjnervosia.gerenciador_encomendas.morador.dto.MoradorResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoradorService {

    private final MoradorRepository moradorRepository;
    private final ApartamentoRepository apartamentoRepository;

    public MoradorService(MoradorRepository moradorRepository, ApartamentoRepository apartamentoRepository) {
        this.moradorRepository = moradorRepository;
        this.apartamentoRepository = apartamentoRepository;
    }

    public MoradorResponseDTO cadastrar(String nome, Long apartamentoId) {
        Apartamento apartamento = apartamentoRepository.findById(apartamentoId).orElseThrow(() -> new ApartamentoNaoEncontradoException(apartamentoId));
        Morador morador = new Morador(nome, apartamento);

        Morador moradorSalvo = moradorRepository.save(morador);

        return new MoradorResponseDTO(moradorSalvo.getId(), moradorSalvo.getNome(), apartamento.getId(), apartamento.getNumero());


    }

    public List<MoradorResponseDTO> listar() {
        List<Morador> moradores = moradorRepository.findAll();
        return moradores.stream()
                .map(morador -> new MoradorResponseDTO(
                        morador.getId(),
                        morador.getNome(),
                        morador.getApartamento().getId(),
                        morador.getApartamento().getNumero()
                )).toList();

    }

    public MoradorResponseDTO buscarPorId(Long id) {
        Morador morador = moradorRepository.findById(id).orElseThrow(() -> new MoradorNaoEncontradoException(id));
        return new MoradorResponseDTO(
                morador.getId(),
                morador.getNome(),
                morador.getApartamento().getId(),
                morador.getApartamento().getNumero()
        );
    }
}
