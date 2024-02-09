package project.todomanagement.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import project.todomanagement.dto.TodoDto;
import project.todomanagement.service.TodoService;

import java.util.List;

@RestController()
@RequestMapping("/api/todos")
@AllArgsConstructor
public class TodoController {
    private TodoService todoService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo( @RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(todoService.addTodo(todoDto),
                HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id) {

        return new ResponseEntity<>(todoService.getTodo(id),
                HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        return new ResponseEntity<>(todoService.getAllTodos(),
                HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id) {
        todoService.removeTodo(id);
        return new ResponseEntity<>("Todo successfully deleted!", HttpStatus.OK);


    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long id, @RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(todoService.updateTodo(todoDto,id),
                HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.completeTodo(id),
                HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable Long id) {
        return new ResponseEntity<>(todoService.inCompleteTodo(id),
                HttpStatus.OK);
    }

}
