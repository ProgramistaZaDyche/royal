package royal.gambit.zadanie.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import royal.gambit.zadanie.DTOs.ShowTaskDTO;
import royal.gambit.zadanie.Entities.TaskEntity;
import royal.gambit.zadanie.Mappers.TasksMapper;
import royal.gambit.zadanie.Repositories.TasksRepository;

@Service
@RequiredArgsConstructor
public class TasksService {
    private final TasksRepository tasksRepository;
    private final TasksMapper tasksMapper;

    public List<ShowTaskDTO> findTasks(
            ShowTaskDTO taskFilter) {
        TaskEntity taskEntity = tasksMapper.showTaskDTOToEntity(taskFilter);
        Example<TaskEntity> example = Example.of(taskEntity);
        return tasksRepository.findAll(example).stream()
                .map(tasksMapper::TaskEntityToShowDTO)
                .collect(Collectors.toList());
    }
}
