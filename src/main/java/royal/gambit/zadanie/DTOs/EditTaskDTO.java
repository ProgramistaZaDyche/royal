package royal.gambit.zadanie.DTOs;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EditTaskDTO {
    private String content;
    private LocalDate editionDate;
}
