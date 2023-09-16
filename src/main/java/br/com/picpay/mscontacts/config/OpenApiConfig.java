package br.com.picpay.mscontacts.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Api portal OCC")
                                 .description("Api para gerenciamento de contatos do Command center Picpay/Original")
                                 .version("1.0.0"));
        
    }
   }