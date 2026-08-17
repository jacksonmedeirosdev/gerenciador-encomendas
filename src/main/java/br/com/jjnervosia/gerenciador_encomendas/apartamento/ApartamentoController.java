package br.com.jjnervosia.gerenciador_encomendas.apartamento;

import br.com.jjnervosia.gerenciador_encomendas.apartamento.dto.ApartamentoResponseDTO;
import br.com.jjnervosia.gerenciador_encomendas.apartamento.dto.AtualizarApartamentoDTO;
import br.com.jjnervosia.gerenciador_encomendas.apartamento.dto.CadastrarApartamentoDTO;
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
@RequestMapping("/apartamentos")
@Tag(name = "Apartamentos", description = "Operação para gerenciamento de apartamentos do condomínio")
public class ApartamentoController {

    private final ApartamentoService apartamentoService;

    public ApartamentoController(ApartamentoService apartamentoService) {
        this.apartamentoService = apartamentoService;
    }

    @Operation(summary = "Cadastrar apartamento", description = "Cadastrar um novo apartamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Apartamento cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Bloco não encontrado", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409", description = "Já existe apartamento com esse número no bloco", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)))
    })
    @PostMapping
    public ResponseEntity<ApartamentoResponseDTO> cadastrar(@Valid @RequestBody CadastrarApartamentoDTO dto) {
        ApartamentoResponseDTO apartamentoResponseDTO = apartamentoService.cadastrar(dto.numero(), dto.blocoId());
        return ResponseEntity.status(HttpStatus.CREATED).body(apartamentoResponseDTO);
    }

    @Operation(summary = "Listar apartamentos", description = "Listar todos apartamentos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de apartamentos retornada com sucesso, inclusive vazia")
    })
    @GetMapping
    public ResponseEntity<List<ApartamentoResponseDTO>> listar() {
        List<ApartamentoResponseDTO> apartamentosResponseDTO = apartamentoService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(apartamentosResponseDTO);

    }


    @Operation(summary = "Buscar apartamento por ID", description = "Buscar um apartamento pelo seu identificador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apartamento encontrado"),
            @ApiResponse(responseCode = "400", description = "Identificador com formato inválido", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Apartamento não encontrado", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApartamentoResponseDTO> buscarPorId(@PathVariable Long id) {
        ApartamentoResponseDTO apartamentoResponseDTO = apartamentoService.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(apartamentoResponseDTO);

    }


    @Operation(summary = "Atualizar apartamento", description = "Atualizar os dados de um apartamento cadastrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apartamento atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados da requisição inválidos", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Apartamento ou bloco não encontrado", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "409", description = "Já existe outro apartamento com esse número no bloco", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApartamentoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody AtualizarApartamentoDTO dto) {
        ApartamentoResponseDTO apartamentoResponseDTO = apartamentoService.atualizar(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(apartamentoResponseDTO);
    }


    @Operation(summary = "Remover apartamento", description = "Remove um apartamento cadastrado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Apartamento removido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Identificador com formato inválido", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class))),
            @ApiResponse(responseCode = "404", description = "Apartamento não encontrado", content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ApiError.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        apartamentoService.remover(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
