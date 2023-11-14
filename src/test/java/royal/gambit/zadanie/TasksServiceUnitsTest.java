package royal.gambit.zadanie;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

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
    public void findTasksCorrectParameters() {
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
}