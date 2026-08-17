package br.com.jjnervosia.gerenciador_encomendas.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;



    @Configuration
    @OpenAPIDefinition(
            info = @Info(
                    title = "Gerenciador de Encomendas API",
                    version = "1.0",
                    description = "API REST para gerenciamento de blocos, apartamentos e encomendas em condomínios residenciais."
            )
    )
    public class OpenApiConfig{}

