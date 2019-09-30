package gloncak.jozef.springframework.boot.web.banner;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication springApp = new SpringApplication(Application.class);
        springApp.setBanner(new Banner() {
            @Override
            public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
                out.print("\n");
                out.print("============\n");
                out.print("====Banner==\n");
                out.print("============\n");
            }
        });
        springApp.run(args);
    }
}