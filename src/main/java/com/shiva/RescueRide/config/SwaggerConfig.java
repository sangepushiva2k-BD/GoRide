package com.shiva.RescueRide.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI vehiclePullingOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RescueRide API")
                        .version("1.0")
                        .description("API documentation for RescueRide Application"))
                .servers(List.of(
                        new Server().url("/").description("Default Server URL")
                ));
    }
}
