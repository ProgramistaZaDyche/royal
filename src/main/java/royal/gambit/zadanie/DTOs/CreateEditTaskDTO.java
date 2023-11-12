package royal.gambit.zadanie.DTOs;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CreateEditTaskDTO {
    private String content;
    private LocalDate creationDate;
    private LocalDate editionDate;
}
