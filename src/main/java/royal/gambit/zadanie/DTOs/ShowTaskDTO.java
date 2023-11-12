package royal.gambit.zadanie.DTOs;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShowTaskDTO {
	private Long id;
    private String content;
    private LocalDate creationDate;
    private LocalDate editionDate;
}
