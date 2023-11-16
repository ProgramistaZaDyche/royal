package royal.gambit.zadanie.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import royal.gambit.zadanie.DTOs.CreateTaskDTO;
import royal.gambit.zadanie.DTOs.EditTaskDTO;
import royal.gambit.zadanie.DTOs.ShowTaskDTO;
import royal.gambit.zadanie.Entities.TaskEntity;
import royal.gambit.zadanie.Exceptions.ValidationException;
import royal.gambit.zadanie.Exceptions.NonExistentRecordException;
import royal.gambit.zadanie.Mappers.TasksMapper;
import royal.gambit.zadanie.Repositories.TasksRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class TasksService {
    private final TasksRepository tasksRepository;
    private final TasksMapper tasksMapper;

    public List<ShowTaskDTO> findTasks(
            ShowTaskDTO taskFilter) {
        log.debug("Received filter: {}", taskFilter);

        TaskEntity taskEntity = tasksMapper.showTaskDTOToEntity(taskFilter);
        Example<TaskEntity> example = Example.of(taskEntity);
        log.trace("Filter mapped to entity: {}", taskEntity);

        List<TaskEntity> result = tasksRepository.findAll(example);
        log.debug("Found list of entities: {}", result);

        return result.stream()
                .map(tasksMapper::TaskEntityToShowDTO)
                .collect(Collectors.toList());
    }

    public ShowTaskDTO findTask(Long id) {
        log.debug("Received id: {}", id);

        TaskEntity taskEntity = findTaskById(id);
        log.debug("Found entity: {}", taskEntity);

        return tasksMapper.TaskEntityToShowDTO(taskEntity);
    }

    public ShowTaskDTO createTask(CreateTaskDTO createTaskDTO) {
        log.debug("Received createDTO: {}", createTaskDTO);
        validateCreateTaskDTO(createTaskDTO);

        TaskEntity entityToCreate = tasksMapper.createTaskDTOToEntity(createTaskDTO);
        TaskEntity newEntity = tasksRepository.save(entityToCreate);
        log.trace("Created entity: {}", newEntity);

        return tasksMapper.TaskEntityToShowDTO(newEntity);
    }

    public ShowTaskDTO editTask(Long id, EditTaskDTO editTaskDTO) {
        log.debug("Received editTaskDTO {} and id {} ", editTaskDTO, id);
        validateEditTaskDTO(editTaskDTO);
        TaskEntity existingEntity = findTaskById(id);

        TaskEntity editEntity = tasksMapper.editTaskDTOToEntity(editTaskDTO);
        editEntity.setId(id);
        editEntity.setCreationDate(existingEntity.getCreationDate());

        TaskEntity newEntity = tasksRepository.save(editEntity);
        log.trace("Edited entity: {}", newEntity);

        return tasksMapper.TaskEntityToShowDTO(newEntity);
    }

    public void deleteTask(Long id) {
        log.debug("Received id: {}", id);
        TaskEntity taskEntity = findTaskById(id);

        tasksRepository.delete(taskEntity);
        log.trace("Deleted entity: {}", taskEntity);
    }

    private TaskEntity findTaskById(Long id) {
        log.trace("Checking whether record with given id {} exists", id);
        return tasksRepository.findById(id)
                .orElseThrow(() -> new NonExistentRecordException(String.format("There is no task with id = %d", id)));
    }

    private void validateCreateTaskDTO(CreateTaskDTO createTaskDTO) {
        log.traceEntry("Validating createTaskDTO {}", createTaskDTO);
        StringBuilder sb = new StringBuilder();

        if (createTaskDTO.getContent() == null) {
            log.error("Attribute content has illegal value: {}", createTaskDTO.getContent());
            sb.append("Content cannot be null!\n");
        }
        if (createTaskDTO.getCreationDate() == null) {
            log.error("Attribute creationDate has illegal value: {}", createTaskDTO.getCreationDate());
            sb.append("Creation date cannot be null!\n");
        }

        if (sb.length() > 0) {
            throw new ValidationException(sb.toString(), "CreateTask");
        }
    }

    private void validateEditTaskDTO(EditTaskDTO editTaskDTO) {
        log.trace("Validating editTaskDTO {}", editTaskDTO);
        StringBuilder sb = new StringBuilder();

        if (editTaskDTO.getContent() == null) {
            log.error("Attribute content has illegal value: {}", editTaskDTO.getContent());
            sb.append("Content cannot be null!\n");
        }
        if (editTaskDTO.getEditionDate() == null) {
            log.error("Attribute editionDate has illegal value: {}", editTaskDTO.getEditionDate());
            sb.append("Edition date cannot be null!\n");
        }

        if (sb.length() > 0) {
            throw new ValidationException(sb.toString(), "EditTask");
        }
    }
}
