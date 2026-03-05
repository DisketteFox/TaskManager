package dev.diskettefox.taskmanager.repositories;

import dev.diskettefox.taskmanager.models.TaskModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends MongoRepository<TaskModel, String> {
}