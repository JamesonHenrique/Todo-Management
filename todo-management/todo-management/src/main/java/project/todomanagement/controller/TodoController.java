package project.todomanagement.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.todomanagement.dto.TodoDto;
import project.todomanagement.service.TodoService;

import java.util.List;

@RestController()
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {
    private TodoService todoService;
    @PostMapping
    public ResponseEntity<TodoDto> addTodo( @RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(todoService.addTodo(todoDto),
                HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id) {

        return new ResponseEntity<>(todoService.getTodo(id),
                HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        return new ResponseEntity<>(todoService.getAllTodos(),
                HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.removeTodo(id);
        return new ResponseEntity<>("Todo successfully deleted!", HttpStatus.OK);

    }
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(todoService.updateTodo(todoDto,id),
                HttpStatus.OK);
    }
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.completeTodo(id),
                HttpStatus.OK);
    }

}
