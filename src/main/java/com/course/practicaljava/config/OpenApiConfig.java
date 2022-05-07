package com.course.practicaljava.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI practicalJavaOpenApi() {
        var info = new Info()
            .title("Practical Java API")
            .description("OpenApi (Swagger) doc")
            .version("1.0");
        return new OpenAPI().info(info);
    }

}
