package br.com.jjnervosia.gerenciador_encomendas.bloco;


import br.com.jjnervosia.gerenciador_encomendas.bloco.dto.AtualizarBlocoDTO;
import br.com.jjnervosia.gerenciador_encomendas.bloco.dto.BlocoResponseDTO;
import br.com.jjnervosia.gerenciador_encomendas.bloco.dto.CadastrarBlocoDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blocos")
public class
BlocoController {

    private final BlocoService blocoService;
    public BlocoController(BlocoService blocoService) {
        this.blocoService = blocoService;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody  CadastrarBlocoDTO dto) {
        blocoService.cadastrar(dto.identificacao());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlocoResponseDTO> atualizar( @Valid @RequestBody AtualizarBlocoDTO dto, @PathVariable Long id ) {
        BlocoResponseDTO Responsedto = blocoService.atualizar(dto, id);
        return ResponseEntity.status(HttpStatus.OK).body(Responsedto);
    }

    @GetMapping
    public ResponseEntity<List<BlocoResponseDTO>>  listar() {
        List<BlocoResponseDTO> listaBlocos = blocoService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(listaBlocos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlocoResponseDTO> buscaPorId(@PathVariable Long id) {

        BlocoResponseDTO dto = blocoService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
}
