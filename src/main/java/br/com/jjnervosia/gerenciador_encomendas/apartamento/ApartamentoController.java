package br.com.jjnervosia.gerenciador_encomendas.apartamento;

import br.com.jjnervosia.gerenciador_encomendas.apartamento.dto.ApartamentoResponseDTO;
import br.com.jjnervosia.gerenciador_encomendas.apartamento.dto.CadastrarApartamentoDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apartamentos")
public class ApartamentoController {

    private final ApartamentoService apartamentoService;
    public ApartamentoController(ApartamentoService apartamentoService) {
        this.apartamentoService = apartamentoService;
    }

    @PostMapping
    public ResponseEntity<ApartamentoResponseDTO> cadastrar(@Valid @RequestBody CadastrarApartamentoDTO dto){
        ApartamentoResponseDTO apartamentoResponseDTO = apartamentoService.cadastrar(dto.numero(), dto.blocoId());
        return ResponseEntity.status(HttpStatus.CREATED).body(apartamentoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<ApartamentoResponseDTO>> listar(){
        List<ApartamentoResponseDTO> apartamentosResponseDTO = apartamentoService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(apartamentosResponseDTO);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApartamentoResponseDTO> buscarPorId(@PathVariable Long id){
        ApartamentoResponseDTO apartamentoResponseDTO = apartamentoService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(apartamentoResponseDTO);

    }
}
