package gloncak.jozef.springboot.restfulwebservice.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.List;

public class UnifiedExceptonResponse {

    private LocalDate date;

    private HttpStatus httpStatus;

    private List<String> mesage;

    public UnifiedExceptonResponse(LocalDate date, HttpStatus httpStatus, List<String> mesage) {
        this.date = date;
        this.httpStatus = httpStatus;
        this.mesage = mesage;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setMesage(List<String> mesage) {
        this.mesage = mesage;
    }

    public LocalDate getDate() {
        return date;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public List<String> getMesage() {
        return mesage;
    }
}
