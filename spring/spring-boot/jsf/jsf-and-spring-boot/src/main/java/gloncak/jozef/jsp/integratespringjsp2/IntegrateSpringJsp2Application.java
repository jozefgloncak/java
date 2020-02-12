package gloncak.jozef.jsp.integratespringjsp2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("gloncak.jozef.jsp.integratespringjsp2")
public class IntegrateSpringJsp2Application extends SpringBootServletInitializer  {

	public static void  main(String[] args) {
		SpringApplication.run(IntegrateSpringJsp2Application.class, args);
	}

}
