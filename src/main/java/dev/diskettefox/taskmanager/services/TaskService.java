package dev.diskettefox.taskmanager.services;

import dev.diskettefox.taskmanager.models.TaskModel;
import dev.diskettefox.taskmanager.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ExecutorService executorService;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.executorService = Executors.newCachedThreadPool();
    }

    public TaskModel createTask(TaskModel taskModel) {
        executorService.submit(() -> {
            try {
                Thread.sleep(5000); // 5 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            taskModel.setCompleted(true);
            taskRepository.save(taskModel);
        });
        return taskRepository.save(taskModel);
    }

    public Iterable<TaskModel> getTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
}