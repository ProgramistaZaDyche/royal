package royal.gambit.zadanie;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import royal.gambit.zadanie.DTOs.CreateTaskDTO;
import royal.gambit.zadanie.DTOs.ShowTaskDTO;
import royal.gambit.zadanie.Entities.TaskEntity;
import royal.gambit.zadanie.Mappers.TasksMapper;
import royal.gambit.zadanie.Repositories.TasksRepository;
import royal.gambit.zadanie.Services.TasksService;

@ExtendWith(MockitoExtension.class)
public class TasksServiceUnitsTest {

    @InjectMocks
    private TasksService tasksService;

    @Mock
    private TasksRepository tasksRepository;

    @Mock
    private TasksMapper tasksMapper;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findTasksCorrectDataPassing() {
        ShowTaskDTO filterDTO = ShowTaskDTO.builder()
                .content("Tests")
                .build();

        TaskEntity filterEntity = TaskEntity.builder()
                .content("Tests")
                .build();

        Example<TaskEntity> filterExample = Example.of(filterEntity);

        when(tasksMapper.showTaskDTOToEntity(filterDTO)).thenReturn(filterEntity);
        when(tasksRepository.findAll(filterExample)).thenReturn(anyList());

        tasksService.findTasks(filterDTO);
    }

    @Test
    public void findTaskCorrectDataPassing() {
        Long id = new Long(4);
        TaskEntity foundEntity = TaskEntity.builder()
                .id(id)
                .build();

        when(tasksRepository.findById(new Long(4))).thenReturn(Optional.of(foundEntity));
        when(tasksMapper.TaskEntityToShowDTO(foundEntity)).thenReturn(any(ShowTaskDTO.class));

        tasksService.findTask(id);
    }

    @Test
    public void createTaskCorrectDataPassing() {
        String content = "some content";
        LocalDate creationDate = LocalDate.of(2023, 2, 14);
        CreateTaskDTO createTaskDTO = CreateTaskDTO.builder()
                .content(content)
                .creationDate(creationDate)
                .build();
        TaskEntity mappedEntity = TaskEntity.builder()
                .content(content)
                .creationDate(creationDate)
                .build();
        TaskEntity newEntity = TaskEntity.builder()
                .id(new Long(4))
                .content(content)
                .creationDate(creationDate)
                .build();

        when(tasksMapper.createTaskDTOToEntity(createTaskDTO)).thenReturn(mappedEntity);
        when(tasksRepository.save(mappedEntity)).thenReturn(newEntity);
        when(tasksMapper.TaskEntityToShowDTO(newEntity)).thenReturn(any(ShowTaskDTO.class));

        tasksService.createTask(createTaskDTO);
    }
}