package br.com.jjnervosia.gerenciador_encomendas.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
                null
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
}
