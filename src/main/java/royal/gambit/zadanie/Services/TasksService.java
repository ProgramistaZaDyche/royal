package royal.gambit.zadanie.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import royal.gambit.zadanie.DTOs.CreateTaskDTO;
import royal.gambit.zadanie.DTOs.EditTaskDTO;
import royal.gambit.zadanie.DTOs.ShowTaskDTO;
import royal.gambit.zadanie.Entities.TaskEntity;
import royal.gambit.zadanie.Exceptions.InvalidDTOException;
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
        validateCreateTaskDTO(createTaskDTO);
        TaskEntity entityToCreate = tasksMapper.createTaskDTOToEntity(createTaskDTO);
        
        TaskEntity newEntity = tasksRepository.save(entityToCreate);
        return tasksMapper.TaskEntityToShowDTO(newEntity);
    }

    public ShowTaskDTO editTask(Long id, EditTaskDTO editTaskDTO) {
        validateEditTaskDTO(editTaskDTO);
        TaskEntity existingEntity = findTaskById(id);

        TaskEntity editEntity = tasksMapper.editTaskDTOToEntity(editTaskDTO);
        editEntity.setId(id);
        editEntity.setCreationDate(existingEntity.getCreationDate());

        TaskEntity newEntity = tasksRepository.save(editEntity);
        return tasksMapper.TaskEntityToShowDTO(newEntity);
    }

    private TaskEntity findTaskById(Long id) {
        return tasksRepository.findById(id)
                .orElseThrow(() -> new NonExistentRecordException(String.format("There is no task with id = %d", id)));
    }

    private void validateCreateTaskDTO(CreateTaskDTO createTaskDTO) {
        StringBuilder sb = new StringBuilder();
        if (createTaskDTO.getContent() == null) {
            sb.append("Content cannot be null!\n");
        }
        if (createTaskDTO.getCreationDate() == null) {
            sb.append("Creation date cannot be null!\n");
        }

        if (sb.length() > 0) {
            throw new InvalidDTOException(sb.toString(), "CreateTask");
        }
    }

    private void validateEditTaskDTO(EditTaskDTO editTaskDTO) {
        StringBuilder sb = new StringBuilder();
        if (editTaskDTO.getContent() == null) {
            sb.append("Content cannot be null!\n");
        }
        if (editTaskDTO.getEditionDate() == null) {
            sb.append("Edition date cannot be null!\n");
        }

        if (sb.length() > 0) {
            throw new InvalidDTOException(sb.toString(), "EditTask");
        }
    }
}
