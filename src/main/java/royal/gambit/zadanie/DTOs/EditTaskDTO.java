package royal.gambit.zadanie.DTOs;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EditTaskDTO {
    private String content;
    private LocalDate editionDate;
}
