package royal.gambit.zadanie.DTOs;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CreateTaskDTO {
    private String content;
    private LocalDate creationDate;
}
