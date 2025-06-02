package com.rado.consumer.configuration.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // Swagger description for WebSocket controller
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("WebSocket API Documentation")
                        .version("1.0")
                        .description("API documentation for WebSocket communication"))
                .addTagsItem(new Tag().name("WebSocket Controller").description("Processes WebSocket messages"));
    }
}