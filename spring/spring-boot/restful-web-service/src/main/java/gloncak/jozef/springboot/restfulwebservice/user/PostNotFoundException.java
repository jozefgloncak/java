package gloncak.jozef.springboot.restfulwebservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(int id, String location) {
        super(String.format("No post with %s was found at %s location", id, location));
    }

    public PostNotFoundException(int id) {
        super(String.format("No post with %s was found", id));
    }
}
