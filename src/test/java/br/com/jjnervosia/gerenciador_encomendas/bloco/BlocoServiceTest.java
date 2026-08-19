package br.com.jjnervosia.gerenciador_encomendas.bloco;

import br.com.jjnervosia.gerenciador_encomendas.apartamento.ApartamentoRepository;
import br.com.jjnervosia.gerenciador_encomendas.bloco.dto.BlocoResponseDTO;
import br.com.jjnervosia.gerenciador_encomendas.exception.BlocoJaExisteException;
import br.com.jjnervosia.gerenciador_encomendas.exception.BlocoNaoEncontradoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

        import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BlocoServiceTest {

    @Mock
    private BlocoRepository repository;

    @Mock
    private ApartamentoRepository apartamentoRepository;

    @InjectMocks
    private BlocoService service;

    @Test
    void deveCadastrarBlocoQuandoIdentificacaoNaoExistir(){
//       Arrange - Preparar
        when(repository.existsByIdentificacao("A")).thenReturn(false);
//       Act - Executar
        service.cadastrar("A");

//        Assert - Verificar
        verify(repository).save(any(Bloco.class));
    }

    @Test
    void deveLancarExcecaoQuandoBlocoJaExistir(){

//        Arrange
        when(repository.existsByIdentificacao("A")).thenReturn(true);

//        Act + Assert
        assertThrows(
                BlocoJaExisteException.class,
                () -> service.cadastrar("A")
        );

        verify(repository, never()).save(any(Bloco.class));
    }

    @Test
    void deveRetornarBlocoQuandoIdExistir(){
        Bloco  bloco = new Bloco("A");

        when(repository.findById(1L)).thenReturn(Optional.of(bloco));

        BlocoResponseDTO resultado = service.buscarPorId(1L);

        assertEquals("A", resultado.identificacao());
    }

    @Test
    void deveLancarExcecaoQuandoBlocoNaoExistir(){
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(
                BlocoNaoEncontradoException.class,
                () -> service.buscarPorId(1L)
        );
    }
}
