package royal.gambit.zadanie;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import royal.gambit.zadanie.DTOs.SaveTaskDTO;
import royal.gambit.zadanie.DTOs.ShowTaskDTO;
import royal.gambit.zadanie.Entities.TaskEntity;
import royal.gambit.zadanie.Mappers.TasksMapper;
import royal.gambit.zadanie.Mappers.TasksMapperImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TasksMapperImpl.class)
public class TasksMapperUnitTest {
    @Autowired
    private TasksMapper tasksMapper;

    @Test
    public void saveTaskDTOCreateEntity() {
        LocalDate today = LocalDate.now();
        SaveTaskDTO dto = SaveTaskDTO.builder()
                .content("content")
                .build();

        TaskEntity entity = tasksMapper.saveTaskDTOCreateEntity(dto);

        assertNull(entity.getId());
        assertEquals(dto.getContent(), entity.getContent());
        assertEquals(today, entity.getCreationDate());
        assertEquals(today, entity.getEditionDate());
    }

    @Test
    public void saveTaskDTOEditEntity() {
        LocalDate today = LocalDate.now();
        SaveTaskDTO dto = SaveTaskDTO.builder()
                .content("content")
                .build();

        TaskEntity entity = tasksMapper.saveTaskDTOEditEntity(dto);

        assertNull(entity.getId());
        assertEquals(dto.getContent(), entity.getContent());
        assertNull(entity.getCreationDate());
        assertEquals(today, entity.getEditionDate());
    }

    @Test
    public void showTaskDTOToEntity() {
        ShowTaskDTO dto = ShowTaskDTO.builder()
                .id(Long.valueOf(2))
                .content("content")
                .creationDate(LocalDate.of(2013, 12, 22))
                .editionDate(LocalDate.of(2014, 10, 19))
                .build();

        TaskEntity entity = tasksMapper.showTaskDTOToEntity(dto);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getContent(), entity.getContent());
        assertEquals(dto.getCreationDate(), entity.getCreationDate());
        assertEquals(dto.getEditionDate(), entity.getEditionDate());
    }

    @Test
    public void taskEntityToEditDTO() {
        TaskEntity entity = TaskEntity.builder()
                .id(Long.valueOf(1))
                .content("content")
                .creationDate(LocalDate.of(2013, 12, 22))
                .editionDate(LocalDate.of(2014, 10, 19))
                .build();

        SaveTaskDTO dto = tasksMapper.TaskEntityToEditDTO(entity);

        assertEquals(entity.getContent(), dto.getContent());
    }

    @Test
    public void taskEntityToShowDTO() {
        TaskEntity entity = TaskEntity.builder()
                .id(Long.valueOf(1))
                .content("content")
                .creationDate(LocalDate.of(2013, 12, 22))
                .editionDate(LocalDate.of(2014, 10, 19))
                .build();

        ShowTaskDTO dto = tasksMapper.TaskEntityToShowDTO(entity);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getContent(), dto.getContent());
        assertEquals(entity.getCreationDate(), dto.getCreationDate());
        assertEquals(entity.getEditionDate(), dto.getEditionDate());
    }
}
