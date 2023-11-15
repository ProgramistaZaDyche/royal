package royal.gambit.zadanie.Exceptions;

import royal.gambit.zadanie.DTOs.CustomExceptionDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ResponseEntity<CustomExceptionDTO> handleCustomException(
            CustomException ex) {
        CustomExceptionDTO dto = ex.toDTO();

        return new ResponseEntity<>(dto, null, ex.getStatus());
    }
}