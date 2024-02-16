package web.restapiproject.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){
        Info info = new Info()
                .title("restAPI 및 JPA(DSL) 스터디용")
                .version("0.1")
                .description("개발 공부용")
                ;

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
