package dev.diskettefox.taskmanager.controllers;

import dev.diskettefox.taskmanager.models.TaskModel;
import dev.diskettefox.taskmanager.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/createTask")
    public ResponseEntity<TaskModel> createTask(@RequestBody TaskModel taskModel) {
        TaskModel taskModelCreated = taskService.createTask(taskModel);
        return new ResponseEntity<>(taskModelCreated, HttpStatus.CREATED);
    }

    @GetMapping("/getTasks")
    public ResponseEntity<Iterable<TaskModel>> getTasks() {
        Iterable<TaskModel> tasks = taskService.getTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable String id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
