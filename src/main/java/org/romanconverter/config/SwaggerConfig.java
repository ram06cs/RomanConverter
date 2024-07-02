package org.romanconverter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Roman Numeral Converter API")
                        .version("1.0")
                        .description("API for converting integers to Roman numerals")
                        .contact(new Contact()
                                .name("Ramkumar Balasubramani")
                                .url("https://www.linkedin.com/in/ram14mis/")
                                .email("ram.06cs@gmail.com")));
    }
}
