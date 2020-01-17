package gloncak.jozef.springboot.restfulwebservice.versioning;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

    //URI param versioning
    @RequestMapping(method = RequestMethod.GET, name = "/versioning/person/uri", params = "v=1")
    public PersonA uriParamPersonA() {
        return new PersonA("Marian Miezga");
    }

    @RequestMapping(method = RequestMethod.GET, name = "/versioning/person/uri", params = "v=2")
    public PersonB uriParamPersonB() {
        return new PersonB(new Name("Marian", "Miezga"));
    }
    //:URI param versioning

    //header versioning
    @RequestMapping(method = RequestMethod.GET, name = "/versioning/person/header", headers = "v=1")
    public PersonA headerPersonA() {
        return new PersonA("Marian Miezga");
    }

    @RequestMapping(method = RequestMethod.GET, name = "/versioning/person/header", headers = "v=2")
    public PersonB headerPersonB() {
        return new PersonB(new Name("Marian", "Miezga"));
    }
    //:header versioning

    //MIME versioning
    @RequestMapping(method = RequestMethod.GET, name = "/versioning/person/header", produces = "application/v1+json")
    public PersonA mimePersonA() {
        return new PersonA("Marian Miezga");
    }

    @RequestMapping(method = RequestMethod.GET, name = "/versioning/person/header", produces = "application/v2+json")
    public PersonB mimePersonB() {
        return new PersonB(new Name("Marian", "Miezga"));
    }
    //:MIME versioning


}
