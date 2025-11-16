package com.shiva.RescueRide.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI vehiclePullingOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Vehicle Pulling Service API")
                        .description("APIs for Vehicle Pulling / Towing Service")
                        .version("v1.0"));
    }
}
