package br.com.jjnervosia.gerenciador_encomendas.bloco;


import br.com.jjnervosia.gerenciador_encomendas.bloco.dto.CadastrarBlocoDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blocos")
public class BlocoController {

    private final BlocoService blocoService;
    public BlocoController(BlocoService blocoService) {
        this.blocoService = blocoService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody  CadastrarBlocoDTO dto) {
        blocoService.cadastrar(dto.identificacao());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
