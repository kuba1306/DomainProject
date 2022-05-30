package com.crud.tasks.service;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {

    private final TaskRepository repository;

    public DbService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Task getTask(final Long taskId)throws TaskNotFoundException {
        return repository.findById(taskId).orElseThrow(TaskNotFoundException::new);
    }

    public Task saveTask(final Task task) {
        return repository.save(task);
    }

    public void deleteTask(final Long taskId) throws TaskNotFoundException {
        Task task = repository.findById(taskId).orElseThrow(TaskNotFoundException::new);
        repository.delete(task);
    }
}