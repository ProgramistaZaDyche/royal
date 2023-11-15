package royal.gambit.zadanie.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import royal.gambit.zadanie.DTOs.CreateTaskDTO;
import royal.gambit.zadanie.DTOs.ShowTaskDTO;
import royal.gambit.zadanie.Entities.TaskEntity;
import royal.gambit.zadanie.Exceptions.NonExistentRecordException;
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

    public ShowTaskDTO findTask(Long id) {
        TaskEntity taskEntity = findTaskById(id);
        return tasksMapper.TaskEntityToShowDTO(taskEntity);
    }

    public ShowTaskDTO createTask(CreateTaskDTO createTaskDTO) {
        TaskEntity entityToCreate = tasksMapper.createTaskDTOToEntity(createTaskDTO);
        
        TaskEntity newEntity = tasksRepository.save(entityToCreate);
        return tasksMapper.TaskEntityToShowDTO(newEntity);
    }

    private TaskEntity findTaskById(Long id) {
        return tasksRepository.findById(id)
                .orElseThrow(() -> new NonExistentRecordException(String.format("There is no task with id = %d", id)));
    }
}
