package royal.gambit.zadanie.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomExceptionDTO {
    private String title;
    private String message;
}
