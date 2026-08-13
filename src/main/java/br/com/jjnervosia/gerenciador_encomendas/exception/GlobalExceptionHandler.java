package br.com.jjnervosia.gerenciador_encomendas.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

        HttpStatus status = HttpStatus.CONFLICT;
        ApiError erro = new ApiError(
                status.value(),
                status.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now(),
                List.of()
        );

        return ResponseEntity
                .status(status)
                .body(erro);
    }

    @ExceptionHandler(BlocoNaoEncontradoException.class)
    public ResponseEntity<ApiError> tratarBlocoNaoEncontrado(
            BlocoNaoEncontradoException exception,
            HttpServletRequest request
    ){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError erro = new ApiError(
                status.value(),
                status.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now(),
                List.of()
        );
        return ResponseEntity
                .status(status)
                .body(erro);
    }

    @ExceptionHandler(ApartamentoJaExisteNoBlocoException.class)
    public ResponseEntity<ApiError> tratarApartamentoJaExiste(
            ApartamentoJaExisteNoBlocoException exception,
            HttpServletRequest request
    ){
        HttpStatus status = HttpStatus.CONFLICT;
        ApiError erro = new ApiError(
                status.value(),
                status.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now(),
                List.of()
        );

        return ResponseEntity
                .status(status)
                .body(erro);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> tratarErroValidacao(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ){
        List<CampoErro> erros = new ArrayList<>();

        for (FieldError fieldError : exception.getFieldErrors()){
            erros.add(new CampoErro(
                    fieldError.getField(),
                    fieldError.getDefaultMessage()
            ));
        }
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError erro = new ApiError(
                status.value(),
                status.getReasonPhrase(),
                "Um ou mais campos são inválidos",
                request.getRequestURI(),
                LocalDateTime.now(),
                erros
        );
        return ResponseEntity
                .status(status)
                .body(erro);

    }

   @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> tratarMetodoHttpNaoSuportado(
            HttpRequestMethodNotSupportedException exception,
            HttpServletRequest request
   ){
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        ApiError erro = new ApiError(
                status.value(),
                status.getReasonPhrase(),
                "O endpoint '"
                        + request.getRequestURI() +
                        "' não suporta o método HTTP "
                        + exception.getMethod() + ".",
                request.getRequestURI(),
                LocalDateTime.now(),
                List.of()
        );

        return ResponseEntity
                .status(status)
                .body(erro);
    };

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> tratarParametroInvalido(
            MethodArgumentTypeMismatchException exception,
            HttpServletRequest request
     ){

        String mensagem = "O parâmetro '" + exception.getName() + "' recebeu o valor '"+ exception.getValue() +"'," +
                " mas deve ser um número inteiro.";
        
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError erro = new ApiError(
                status.value(),
                status.getReasonPhrase(),
                mensagem,
                request.getRequestURI(),
                LocalDateTime.now(),
                List.of()
        );
        
        return ResponseEntity
                .status(status)
                .body(erro);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiError> tratarEndPointNaoEncontrado(
            NoResourceFoundException exception,
            HttpServletRequest request
    ){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiError erro = new ApiError(
                status.value(),
                status.getReasonPhrase(),
                "O endpoint " + request.getRequestURI() + " não existe",
                request.getRequestURI(),
                LocalDateTime.now(),
                List.of()
        );
        return ResponseEntity
                .status(status)
                .body(erro);
    }
}
