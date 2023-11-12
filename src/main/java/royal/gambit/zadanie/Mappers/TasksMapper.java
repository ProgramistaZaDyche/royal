package royal.gambit.zadanie.Mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import royal.gambit.zadanie.DTOs.CreateTaskDTO;
import royal.gambit.zadanie.DTOs.EditTaskDTO;
import royal.gambit.zadanie.DTOs.ShowTaskDTO;
import royal.gambit.zadanie.Entities.TaskEntity;

@Mapper(componentModel = "spring")
public interface TasksMapper {
    // DTO to Entity block
    TaskEntity createTaskDTOToEntity(CreateTaskDTO taskDTO);

    TaskEntity editTaskDTOToEntity(EditTaskDTO taskDTO);

    @AfterMapping
    default void setFirstEditionDate(CreateTaskDTO taskDTO, @MappingTarget TaskEntity taskEntity) {
        taskEntity.setEditionDate(taskDTO.getCreationDate());
    }

    // Entity to DTO block
    EditTaskDTO TaskEntityToEditDTO(TaskEntity taskEntity);

    ShowTaskDTO TaskEntityToShowDTO(TaskEntity taskEntity);
}
