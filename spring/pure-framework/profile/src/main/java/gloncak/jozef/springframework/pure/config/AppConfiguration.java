package gloncak.jozef.springframework.pure.config;

import gloncak.jozef.springframework.pure.api.beans.Car;
import gloncak.jozef.springframework.pure.beans.CarProductionImpl;
import gloncak.jozef.springframework.pure.beans.CarTestImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

public class AppConfiguration {
    @Bean("car")
    @Profile("test")
    Car testCar() {
        return new CarTestImpl();
    }

    @Bean("car")
    @Profile("production")
    Car productionCar() {
        return new CarProductionImpl();
    }

    @Bean("driver")
    @Profile("test")
    String provideDriver() {
        return "testDriver";
    }

    @Bean("manager")
    @Profile("production")
    String provideManager() {
        return "production manager";
    }
}
