package royal.gambit.zadanie.Exceptions;

import org.springframework.http.HttpStatus;

public class NonExistentRecordException extends CustomException{
    
    public NonExistentRecordException(String message) {
        super(createTitle(), message, HttpStatus.NOT_FOUND);
    }

    private static String createTitle() {
        return "Nonexistent Record Error!";
    }
}
