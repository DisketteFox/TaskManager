package dev.diskettefox.taskmanager;

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

    public Task createTask(Task task) {
        executorService.submit(() -> {
            try {
                Thread.sleep(5000); // 5 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            task.setCompleted(true);
            taskRepository.save(task);
        });
        return taskRepository.save(task);
    }

    public Iterable<Task> getTasks() {
        return taskRepository.findAll();
    }

    public void deleteTask(String id) {
        taskRepository.deleteById(id);
    }
}