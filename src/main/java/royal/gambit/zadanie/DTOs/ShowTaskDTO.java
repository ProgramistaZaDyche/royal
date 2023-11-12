package royal.gambit.zadanie.DTOs;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ShowTaskDTO {
	private Long id;
    private String content;
    private LocalDate creationDate;
    private LocalDate editionDate;
}
