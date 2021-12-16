package gloncak.jozef.rychlyweb.endpoints;

import gloncak.jozef.rychlyweb.endpoints.bean.Hello;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Endpoints {
    private static final Logger LOG = LoggerFactory.getLogger(Hello.class);


    @GetMapping("/hello")
    public String helloWorld() {
        LOG.info("REST - simple greeting");

        return "Hello world";
    }

    @GetMapping("hello-object")
    public Hello helloWorldAsObject() {
        LOG.info("REST - object greeting");
        Hello hello = new Hello();
        hello.setGreeting("Hello world fromobject");
        return hello;
    }

}
