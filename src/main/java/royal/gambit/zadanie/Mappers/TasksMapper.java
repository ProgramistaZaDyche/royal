package royal.gambit.zadanie.Mappers;

import java.time.LocalDate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import royal.gambit.zadanie.DTOs.SaveTaskDTO;
import royal.gambit.zadanie.DTOs.ShowTaskDTO;
import royal.gambit.zadanie.Entities.TaskEntity;

@Mapper(componentModel = "spring", imports = { LocalDate.class })
public interface TasksMapper {
    // DTO to Entity block
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", expression = "java(LocalDate.now())")
    @Mapping(target = "editionDate", expression = "java(LocalDate.now())")
    TaskEntity saveTaskDTOCreateEntity(SaveTaskDTO taskDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "editionDate", expression = "java(LocalDate.now())")
    TaskEntity saveTaskDTOEditEntity(SaveTaskDTO taskDTO);

    TaskEntity showTaskDTOToEntity(ShowTaskDTO taskDTO);

    // Entity to DTO block
    SaveTaskDTO TaskEntityToEditDTO(TaskEntity taskEntity);

    ShowTaskDTO TaskEntityToShowDTO(TaskEntity taskEntity);
}
