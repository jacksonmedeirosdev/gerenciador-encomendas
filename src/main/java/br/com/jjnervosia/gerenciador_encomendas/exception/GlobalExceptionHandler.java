package br.com.jjnervosia.gerenciador_encomendas.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BlocoJaExisteException.class)
    public ResponseEntity<ApiError> tratarBlocoJaExiste(
            BlocoJaExisteException exception,
            HttpServletRequest request


    ) {
        return criarRespostaErro(
                HttpStatus.CONFLICT,
                exception.getMessage(),
                request,
                List.of()
        );
    }

    @ExceptionHandler(BlocoNaoEncontradoException.class)
    public ResponseEntity<ApiError> tratarBlocoNaoEncontrado(
            BlocoNaoEncontradoException exception,
            HttpServletRequest request
    ) {

        return criarRespostaErro(HttpStatus.NOT_FOUND,
                exception.getMessage(),
                request,
                List.of());

    }

    @ExceptionHandler(ApartamentoJaExisteNoBlocoException.class)
    public ResponseEntity<ApiError> tratarApartamentoJaExiste(
            ApartamentoJaExisteNoBlocoException exception,
            HttpServletRequest request
    ) {
        return criarRespostaErro(
                HttpStatus.CONFLICT,
                exception.getMessage(),
                request,
                List.of()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> tratarErroValidacao(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        List<CampoErro> erros = new ArrayList<>();

        for (FieldError fieldError : exception.getFieldErrors()) {
            erros.add(new CampoErro(
                    fieldError.getField(),
                    fieldError.getDefaultMessage()
            ));
        }

        return criarRespostaErro(HttpStatus.BAD_REQUEST,
                "Um ou mais campos são inválidos",
                request,
                erros);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> tratarMetodoHttpNaoSuportado(
            HttpRequestMethodNotSupportedException exception,
            HttpServletRequest request
    ) {
        String mensagem = "O endpoint '"
                + request.getRequestURI() +
                "' não suporta o método HTTP "
                + exception.getMethod() + ".";
        return criarRespostaErro(HttpStatus.METHOD_NOT_ALLOWED,
                mensagem,
                request,
                List.of());

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> tratarParametroInvalido(
            MethodArgumentTypeMismatchException exception,
            HttpServletRequest request
    ) {

        String mensagem = "O parâmetro da URL '" + exception.getName() +
                "' recebeu o valor '" + exception.getValue() +
                "', mas deve ser um número inteiro.";

        return criarRespostaErro(HttpStatus.BAD_REQUEST,
                mensagem,
                request,
                List.of());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError> tratarEndPointNaoEncontrado(
            NoResourceFoundException exception,
            HttpServletRequest request
    ) {
        String mensagem = "O endpoint " + request.getRequestURI() + " não existe";
        return criarRespostaErro(HttpStatus.NOT_FOUND,
                mensagem,
                request,
                List.of());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> tratarParametroInvalido(
            HttpMessageNotReadableException exception,
            HttpServletRequest request

    ){
        String mensagem =  "O corpo da requisição contém dados inválidos ou incompatíveis com o formato esperado.";
        return criarRespostaErro(
                HttpStatus.BAD_REQUEST,
                mensagem,
                request,
                List.of()
        );

    }

    private ResponseEntity<ApiError> criarRespostaErro(
            HttpStatus status,
            String mensagem,
            HttpServletRequest request,
            List<CampoErro> erros
    ) {
        ApiError erro = new ApiError(
                status.value(),
                status.getReasonPhrase(),
                mensagem,
                request.getRequestURI(),
                LocalDateTime.now(),
                erros
        );
        return ResponseEntity.status(status).body(erro);
    }
}
