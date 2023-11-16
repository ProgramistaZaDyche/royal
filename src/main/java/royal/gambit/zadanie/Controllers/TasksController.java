package royal.gambit.zadanie.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import royal.gambit.zadanie.DTOs.CreateTaskDTO;
import royal.gambit.zadanie.DTOs.EditTaskDTO;
import royal.gambit.zadanie.DTOs.ShowTaskDTO;
import royal.gambit.zadanie.Services.TasksService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TasksController {
    private final TasksService tasksService;

    @GetMapping("")
    @ApiOperation("Get all or filtered tasks")
    public ResponseEntity<List<ShowTaskDTO>> findTasks(
            @Nullable @ModelAttribute ShowTaskDTO taskDTO) {
        return new ResponseEntity<>(tasksService.findTasks(taskDTO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get task by its id")
    public ResponseEntity<ShowTaskDTO> findTask(
            @NonNull @PathVariable Long id) {
        return new ResponseEntity<>(tasksService.findTask(id), HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation("Create new task")
    public ResponseEntity<ShowTaskDTO> createTask(
            @NonNull @RequestBody CreateTaskDTO createTaskDTO) {
        return new ResponseEntity<>(tasksService.createTask(createTaskDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation("Edit existing task")
    public ResponseEntity<ShowTaskDTO> editTask(
            @NonNull @PathVariable Long id,
            @NonNull @RequestBody EditTaskDTO editTaskDTO) {
        return new ResponseEntity<>(tasksService.editTask(id, editTaskDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete existing task")
    public ResponseEntity<Void> deleteTask(
            @NonNull @PathVariable Long id) {
        tasksService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
