package gloncak.jozef.springboot.restfulwebservice.exceptions;

import gloncak.jozef.springboot.restfulwebservice.user.PostNotFoundException;
import gloncak.jozef.springboot.restfulwebservice.user.UserNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.ws.Response;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class, PostNotFoundException.class})
    public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) {
        UnifiedExceptonResponse unifiedResponse = new UnifiedExceptonResponse(LocalDate.now(), HttpStatus.NOT_FOUND,
                Arrays.asList(ex.getMessage()));

        return new ResponseEntity(unifiedResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        UnifiedExceptonResponse unifiedResponse = new UnifiedExceptonResponse(LocalDate.now(), HttpStatus.BAD_REQUEST,
                processResultOfNoValidMethodArgument(ex));
        return new ResponseEntity(unifiedResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEmptyResultDataAccesException(Exception ex, WebRequest request) {
        return new ResponseEntity(
                new UnifiedExceptonResponse(LocalDate.now(), HttpStatus.NOT_FOUND, Arrays.asList(ex.getMessage())),
                HttpStatus.NOT_FOUND);
    }

    private List<String> processResultOfNoValidMethodArgument(MethodArgumentNotValidException ex) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        return allErrors.stream()
                .map(error -> error.getDefaultMessage())
                .collect(Collectors.toList());
    }
}
