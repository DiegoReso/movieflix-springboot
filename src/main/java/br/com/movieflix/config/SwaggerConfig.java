package br.com.movieflix.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;




@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer")
@OpenAPIDefinition(
        info = @Info(
                title = "MovieFlix API",
                version = "1.0.0",
                description = "Documentação da API do MovieFlix, uma plataforma para gerenciar filmes e usuários.",
                contact = @Contact(
                        name = "Diego Reis",
                        email = "diegoreso@me.com",
                        url = "movieflix.diego.reis.dev (descritiva apenas para fins de estudo)"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Servidor de Desenvolvimento Local")
        }
)
public class SwaggerConfig {

}