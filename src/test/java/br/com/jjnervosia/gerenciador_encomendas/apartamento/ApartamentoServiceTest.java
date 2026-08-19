package br.com.jjnervosia.gerenciador_encomendas.apartamento;

import br.com.jjnervosia.gerenciador_encomendas.apartamento.dto.ApartamentoResponseDTO;
import br.com.jjnervosia.gerenciador_encomendas.bloco.Bloco;
import br.com.jjnervosia.gerenciador_encomendas.bloco.BlocoRepository;
import br.com.jjnervosia.gerenciador_encomendas.exception.ApartamentoJaExisteNoBlocoException;
import br.com.jjnervosia.gerenciador_encomendas.exception.ApartamentoNaoEncontradoException;
import br.com.jjnervosia.gerenciador_encomendas.exception.BlocoNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApartamentoServiceTest {

    @Mock
    BlocoRepository blocoRepository;

    @Mock
    ApartamentoRepository apartamentoRepository;

    @InjectMocks
    ApartamentoService service;


    @Test
    void deveCadastrarApartamentoQuandoDadosForemValidos(){

        // Arrange
        Bloco bloco = new Bloco("A");
        Apartamento apartamentoSalvo = new Apartamento("101", bloco);

        when(blocoRepository.findById(1L)).thenReturn(Optional.of(bloco));

        when(apartamentoRepository.existsByNumeroAndBlocoId("101", 1L)).thenReturn(false);

        when(apartamentoRepository.save(any(Apartamento.class))).thenReturn(apartamentoSalvo);

        // Act
        ApartamentoResponseDTO resultado = service.cadastrar("101",1L);

        // Assert
        verify(apartamentoRepository).save(any(Apartamento.class));
        verify(blocoRepository).findById(1L);

        assertEquals("101", resultado.numero());
        assertEquals("A", resultado.blocoIdentificacao());

    }

    @Test
    void deveLancarExcecaoQuandoBlocoNaoEncontrado(){

        when(blocoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                BlocoNaoEncontradoException.class,
                () -> service.cadastrar("101",1L)
        );

        verify(apartamentoRepository, never())
                .save(any(Apartamento.class));

    }

    @Test
    void deveLancarExcecaoSeApartamentoJaExistirNoBloco(){
        Bloco bloco = new Bloco("A");

        when(blocoRepository.findById(1L)).thenReturn(Optional.of(bloco));

        when(apartamentoRepository.existsByNumeroAndBlocoId("101", 1L)).thenReturn(true);

        assertThrows(
                ApartamentoJaExisteNoBlocoException.class,
                () -> service.cadastrar("101",1L)
        );

        verify(apartamentoRepository, never()).save(any(Apartamento.class));
    }

    @Test
    void deveBuscarApartamentoPorId(){
        Bloco bloco = new Bloco("A");
        Apartamento apartamento = new Apartamento("101", bloco);

        when(apartamentoRepository.findById(1L)).thenReturn(Optional.of(apartamento));

        ApartamentoResponseDTO resultado = service.buscarPorId(1L);

        verify(apartamentoRepository).findById(1L);
        assertEquals("101", resultado.numero());
        assertEquals("A", resultado.blocoIdentificacao());
    }

    @Test
    void deveLancarExcecaoSeApartamentoNaoEncontrado(){

        when(apartamentoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                ApartamentoNaoEncontradoException.class,
                () -> service.buscarPorId(1L)
        );

    }
}
