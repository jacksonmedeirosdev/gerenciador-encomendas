package br.com.jjnervosia.gerenciador_encomendas.morador;

import br.com.jjnervosia.gerenciador_encomendas.morador.dto.CadastrarMoradorDTO;
import br.com.jjnervosia.gerenciador_encomendas.morador.dto.MoradorResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moradores")
public class MoradorController {
    private final MoradorService moradorService;
    public MoradorController(MoradorService moradorService) {
        this.moradorService = moradorService;
    }

    @PostMapping
    public ResponseEntity<MoradorResponseDTO> cadastrar(@Valid @RequestBody CadastrarMoradorDTO dto){
        MoradorResponseDTO moradorResponseDTO = moradorService.cadastrar(dto.nome(),  dto.apartamentoId());
        return ResponseEntity.status(HttpStatus.CREATED).body(moradorResponseDTO);

    }

    @GetMapping
    public ResponseEntity<List<MoradorResponseDTO>> listar(){
        List<MoradorResponseDTO> moradores = moradorService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(moradores);
    }
}
