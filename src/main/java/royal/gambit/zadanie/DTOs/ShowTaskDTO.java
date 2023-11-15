package royal.gambit.zadanie.DTOs;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowTaskDTO {
	private Long id;
    private String content;
    private LocalDate creationDate;
    private LocalDate editionDate;
}
