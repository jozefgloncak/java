package gloncak.jozef.springboot.restfulwebservice.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * Following api method is enough to generate REST web service structure documentation available at
 * http://localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static Contact contact = new Contact("name", "http://example.sk", "email@email.sk");
    private static ApiInfo apiInfo = new ApiInfoBuilder()
                    .contact(contact)
                    .description("This is awesome description")
                    .license("My licence")
                    .title("Main title")
                    .version("1.0.0").build();

    private static final Set<String> FORMATS = new HashSet<>(Arrays.asList("application/json","application/xml"));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .produces(FORMATS)
                .consumes(FORMATS);
    }

}
