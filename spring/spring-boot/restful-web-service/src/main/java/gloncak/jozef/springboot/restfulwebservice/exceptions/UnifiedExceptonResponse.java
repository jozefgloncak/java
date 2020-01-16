package gloncak.jozef.springboot.restfulwebservice.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDate;

public class UnifiedExceptonResponse {

    private LocalDate date;

    private HttpStatus httpStatus;

    private String mesage;

    public UnifiedExceptonResponse(LocalDate date, HttpStatus httpStatus, String mesage) {
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

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }

    public LocalDate getDate() {
        return date;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMesage() {
        return mesage;
    }
}
