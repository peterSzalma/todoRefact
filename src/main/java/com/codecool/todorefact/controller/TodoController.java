package com.codecool.todorefact.controller;

import com.codecool.todorefact.entity.Todo;
import com.codecool.todorefact.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Transactional
public class TodoController {
    private static final String SUCCESS = "{\"success\":true}";
    @Autowired
    private TodoRepository todoRepository;

    @PostMapping("/list")
    public List<Todo> findAllTodos(@RequestParam String status) {
        if (status.equals("active")) {
            return todoRepository.findByStatusFalse();
        }
        if (status.equals("complete")) {
            return todoRepository.findByStatusTrue();
        }
        return todoRepository.findAll();
    }

    @PostMapping("/addTodo")
    public void addTodo(@RequestParam("todo-title") String newTodo) {
        todoRepository.save(Todo.builder()
                            .title(newTodo)
                            .status(false)
                            .build());
    }

    @DeleteMapping("/todos/completed")
    public void deleteAllCompletedTodos() {
        todoRepository.findByStatusTrue().forEach(todo -> todoRepository.delete(todo));
    }

    @PutMapping("/todos/toggle_all")
    public void toggleAllStatus(@RequestParam("toggle-all") String status) {
        if (status.equals("true")) {
            todoRepository.findByStatusFalse().forEach(todo -> todo.setStatus(true));
        }else if (status.equals("false")) {
            todoRepository.findByStatusTrue().forEach(todo -> todo.setStatus(false));
        }
    }

    @DeleteMapping("/todos/{id}")
    public void deleteTodoById(@PathVariable("id") Long id) {
        todoRepository.deleteById(id);
    }

    @PutMapping("/todos/{id}")
    public void editTodo(@RequestParam("todo-title") String title, @PathVariable Long id) {
        todoRepository.findById(id).ifPresent(toDo -> toDo.setTitle(title));
    }

    @PutMapping("/todos/{id}/toggle_status")
    public void ToggleStatusById(@RequestParam String status, @PathVariable("id") Long id) {
        if (status.equals("false")) {
            todoRepository.toggleUpdate(true, id);
        } else {
            todoRepository.toggleUpdate(false, id);
        }
    }




}
