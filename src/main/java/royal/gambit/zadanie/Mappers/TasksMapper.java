package royal.gambit.zadanie.Mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import royal.gambit.zadanie.DTOs.CreateTaskDTO;
import royal.gambit.zadanie.DTOs.EditTaskDTO;
import royal.gambit.zadanie.DTOs.ShowTaskDTO;
import royal.gambit.zadanie.Entities.TaskEntity;

@Mapper(componentModel = "spring")
public interface TasksMapper {
    // DTO to Entity block
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "editionDate", source = "creationDate")
    TaskEntity createTaskDTOToEntity(CreateTaskDTO taskDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    TaskEntity editTaskDTOToEntity(EditTaskDTO taskDTO);

    TaskEntity showTaskDTOToEntity(ShowTaskDTO taskDTO);

    // Entity to DTO block
    EditTaskDTO TaskEntityToEditDTO(TaskEntity taskEntity);

    ShowTaskDTO TaskEntityToShowDTO(TaskEntity taskEntity);
}
