package royal.gambit.zadanie.Mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import royal.gambit.zadanie.DTOs.CreateTaskDTO;
import royal.gambit.zadanie.DTOs.EditTaskDTO;
import royal.gambit.zadanie.DTOs.ShowTaskDTO;
import royal.gambit.zadanie.Entities.TaskEntity;

@Mapper(componentModel = "spring")
public interface TasksMapper {
    // DTO to Entity block
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "editionDate", ignore = true)
    TaskEntity createTaskDTOToEntity(CreateTaskDTO taskDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    TaskEntity editTaskDTOToEntity(EditTaskDTO taskDTO);

    TaskEntity showTaskDTOToEntity(ShowTaskDTO taskDTO);

    @AfterMapping
    default void setFirstEditionDate(CreateTaskDTO taskDTO, @MappingTarget TaskEntity taskEntity) {
        taskEntity.setEditionDate(taskDTO.getCreationDate());
    }

    // Entity to DTO block
    EditTaskDTO TaskEntityToEditDTO(TaskEntity taskEntity);

    ShowTaskDTO TaskEntityToShowDTO(TaskEntity taskEntity);
}
