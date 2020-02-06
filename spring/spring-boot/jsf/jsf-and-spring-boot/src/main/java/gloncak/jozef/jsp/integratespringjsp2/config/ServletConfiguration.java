package gloncak.jozef.jsp.integratespringjsp2.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class ServletConfiguration implements ServletContextInitializer {

    @Value("${primefaces.THEME:afterdark}")
    String primafacesTheme; 
    
    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        sc.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", "true");
        sc.setInitParameter("javax.faces.PROJECT_STAGE", "Development");  
        sc.setInitParameter("primefaces.THEME", primafacesTheme);
    }
}
