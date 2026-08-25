package br.com.jjnervosia.gerenciador_encomendas.morador;

import br.com.jjnervosia.gerenciador_encomendas.morador.dto.CadastrarMoradorDTO;
import br.com.jjnervosia.gerenciador_encomendas.morador.dto.MoradorResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
