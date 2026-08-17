package br.com.jjnervosia.gerenciador_encomendas.bloco;


import br.com.jjnervosia.gerenciador_encomendas.bloco.dto.AtualizarBlocoDTO;
import br.com.jjnervosia.gerenciador_encomendas.bloco.dto.BlocoResponseDTO;
import br.com.jjnervosia.gerenciador_encomendas.bloco.dto.CadastrarBlocoDTO;
import br.com.jjnervosia.gerenciador_encomendas.exception.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blocos")
@Tag(name = "Blocos", description = "Operações para gerenciamento de blocos do condomínio")
public class BlocoController {

    private final BlocoService blocoService;

    public BlocoController(BlocoService blocoService) {
        this.blocoService = blocoService;
    }

    @Operation(summary = "Cadastrar bloco", description = "Cadastra um novo bloco do condomínio")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Bloco criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)
            )),
            @ApiResponse(responseCode = "409", description = "Já existe um bloco com esse valor", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)
            ))
    })
    @PostMapping
    public ResponseEntity<Void> cadastrar(@Valid @RequestBody CadastrarBlocoDTO dto) {
        blocoService.cadastrar(dto.identificacao());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Atualizar bloco", description = "Atualiza os dados de um bloco no condomínio")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bloco atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)
            )),
            @ApiResponse(responseCode = "404", description = "Id não encontrado", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)
            )),
            @ApiResponse(responseCode = "409", description = "Já existe um bloco com esse valor", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)
            ))
    })
    @PutMapping("/{id}")
    public ResponseEntity<BlocoResponseDTO> atualizar(@Valid @RequestBody AtualizarBlocoDTO atualizarListaDto, @PathVariable Long id) {
        BlocoResponseDTO responseDto = blocoService.atualizar(atualizarListaDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Operation(summary = "Listar blocos", description = "Listar todos os blocos cadastrados no condomínio")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de blocos retornada, inclusive vazia se for o caso")
    })
    @GetMapping
    public ResponseEntity<List<BlocoResponseDTO>> listar() {
        List<BlocoResponseDTO> listaBlocosResponseDto = blocoService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(listaBlocosResponseDto);
    }

    @Operation(summary = "Buscar bloco por ID", description = "Busca um bloco do condomínio por identificador")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Bloco encontrado"),
            @ApiResponse(responseCode = "400", description = "Identificador no formato inválido, aceita apenas números",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)
                    )),
            @ApiResponse(responseCode = "404", description = "Id não localizado", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)
            ))
    })
    @GetMapping("/{id}")
    public ResponseEntity<BlocoResponseDTO> buscaPorId(@PathVariable Long id) {

        BlocoResponseDTO responseDto = blocoService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @Operation(
            summary = "Remover bloco",
            description = "Remove um bloco do condomínio quando não existem apartamentos vinculados"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Bloco removido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Identificador informado em formato inválido, aceita apenas números", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)
            )),
            @ApiResponse(responseCode = "404", description = "Bloco não encontrado", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)
            )),
            @ApiResponse(responseCode = "409", description = "O bloco possui apartamentos vinculados e não pode ser removido", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)
            ))
    }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {

        blocoService.remover(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
