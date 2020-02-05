package gloncak.jozef.jsp.integratespringjsp2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Is necessary to add configuration from xml file to running spring.
 */
@Configuration
@ImportResource({"classpath*:beans.xml"})
@Import( ServletConfiguration.class)
public class XMLConfiguration {
}
