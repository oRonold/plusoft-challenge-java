package br.com.fiap.challenge.sprint1.infra.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(contact = @Contact(name = "Ronald Oliveira", email = "rm552364@fiap.com.br", url =
                "http://wwww.fiap.com.br"),
                description = "Especificação da API para o challenge 2024 FIAP-PLUSOFT",
                title = "PLUSOFT CHALLENGE API",
                version = "1.0",
                license = @License(name = ""),
                termsOfService = "Termos"
        ),
        servers = {
                @Server(description = "Dev Env", url = "http://localhost:8080"),
                @Server(description = "Prod Env", url = "http://fiapblog.com.br")
        },
        security = @SecurityRequirement(name = "bearerJWT")
)
@SecurityScheme(
        name = "bearerJWT",
        description = "Autenticação JWT",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfiguration {
}
