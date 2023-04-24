package com.alienexplorer.app.rest.Controller;

import com.alienexplorer.app.rest.Model.Task;
import com.alienexplorer.app.rest.Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;
    @GetMapping(value = "/")
    public String message(){
        return "<h1>Hello from Spring Boot</h1>";
    }

    @GetMapping(value = "/getTasks")
    public List<Task> getTasks(){
        return todoRepository.findAll();
    }
    @PostMapping(value = "/saveTask")
    public String saveTask(@RequestBody Task task){
        todoRepository.save(task);
        return "saved task";
    }
    @PutMapping(value = "/update/{id}")
    public String updateTask(@PathVariable Long id, @RequestBody Task task){
        Task updateTask = todoRepository.findById(id).get();
        updateTask.setTitle(task.getTitle());
        updateTask.setDescription(task.getDescription());
        todoRepository.save(updateTask);
        return "Updated Task";
    }
    @DeleteMapping(value = "/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        Task deleteTask = todoRepository.findById(id).get();
        todoRepository.delete(deleteTask);
        return "Deleted Task";
    }
}
