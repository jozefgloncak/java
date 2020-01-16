package gloncak.jozef.springboot.restfulwebservice.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id, String location) {
        super(String.format("No user with %s was found at %s location", id, location));
    }

    public UserNotFoundException(int id) {
        super(String.format("No user with %s was found", id));
    }
}
