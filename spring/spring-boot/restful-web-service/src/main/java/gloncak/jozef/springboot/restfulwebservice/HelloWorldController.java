package gloncak.jozef.springboot.restfulwebservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    MessageSource msgSource;

    //    @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-object")
    public HelloWorldBean helloWorldObject() {
        return new HelloWorldBean("hello from object");
    }

    @GetMapping(path = "/hello-world/var/{name}")
    public HelloWorldBean helloWorldPathVar(@PathVariable String name) {
        return new HelloWorldBean("hello from with my var " + name);
    }

    @RequestMapping(method = RequestMethod.GET, path="/hello-world/i18n")
    public HelloWorldBean helloWorldI18n(@RequestHeader( defaultValue = "es", name = "Accept-Language") Locale locale) {
        return new HelloWorldBean(msgSource.getMessage("greeting", null, locale));
    }
}
