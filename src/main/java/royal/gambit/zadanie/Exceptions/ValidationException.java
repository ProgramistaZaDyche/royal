package royal.gambit.zadanie.Exceptions;

import org.springframework.http.HttpStatus;

public class ValidationException extends CustomException {
    public ValidationException(String message, String DTOName) {
        super(createTitle(DTOName), message, HttpStatus.BAD_REQUEST);
    }

    private static String createTitle(String DTOName) {
        return String.format("%s data structure is invalid!", DTOName);
    }
}
