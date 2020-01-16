package gloncak.jozef.springboot.restfulwebservice.exceptions;

import gloncak.jozef.springboot.restfulwebservice.user.PostNotFoundException;
import gloncak.jozef.springboot.restfulwebservice.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, PostNotFoundException.class})
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        UnifiedExceptonResponse unifiedResponse = new UnifiedExceptonResponse(LocalDate.now(), HttpStatus.NOT_FOUND, ex.getMessage());

        return new ResponseEntity(unifiedResponse, HttpStatus.NOT_FOUND);
    }
}
