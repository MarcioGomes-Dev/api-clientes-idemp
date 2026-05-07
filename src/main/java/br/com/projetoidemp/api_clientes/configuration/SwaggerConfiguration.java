package br.com.projetoidemp.api_clientes.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Clientes API")
                .description("API para gerenciamento de cliente desenvolvida por Marcio Gomes.")
                .version("v1.0.0")
                .contact(new Contact()
                        .name("Marcio Gomes")
                        .email("marciojsg.dev@gmail.com")
                )
        );
    }
}
