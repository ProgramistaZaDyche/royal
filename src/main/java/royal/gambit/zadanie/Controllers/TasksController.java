package royal.gambit.zadanie.Controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
}
