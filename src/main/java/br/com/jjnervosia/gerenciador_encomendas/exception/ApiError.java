package br.com.jjnervosia.gerenciador_encomendas.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        int status,
        String erro,
        String mensagem,
        String path,
        LocalDateTime dataHora,
        List<CampoErro> erros
) {
}
