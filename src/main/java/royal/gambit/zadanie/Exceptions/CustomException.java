package royal.gambit.zadanie.Exceptions;

import org.springframework.http.HttpStatus;

import royal.gambit.zadanie.DTOs.CustomExceptionDTO;

public abstract class CustomException extends RuntimeException{
    private final String title;
    private final HttpStatus status;

    CustomException(String title, String message, HttpStatus status) {
        super(message);
        this.title = title;
        this.status = status;
    }

    public CustomExceptionDTO toDTO() {
        return new CustomExceptionDTO(title, super.getMessage());
    }

    public HttpStatus getStatus() {
        return status;
    }
}
