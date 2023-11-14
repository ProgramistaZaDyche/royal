package royal.gambit.zadanie.DTOs;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTaskDTO {
    private String content;
    private LocalDate creationDate;
}
