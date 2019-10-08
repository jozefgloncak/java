package gloncak.jozef.springframework.pure.bean.postprocesors.config;

import gloncak.jozef.springframework.pure.bean.postprocesors.beans.Car;
import gloncak.jozef.springframework.pure.bean.postprocesors.postprocesor.CustomPostprocesor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }

    @Bean
    public CustomPostprocesor postProcessor() {
        return new CustomPostprocesor();
    }


}
