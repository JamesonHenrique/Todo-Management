package project.todomanagement.service;

import org.springframework.stereotype.Service;
import project.todomanagement.dto.TodoDto;

import java.util.List;
@Service
public interface TodoService {

    TodoDto addTodo(TodoDto todoDto);
    List<TodoDto> getAllTodos();
    TodoDto getTodo(Long Id);
    void removeTodo(Long Id);
    TodoDto updateTodo(
                       TodoDto todoDto,Long id);
    TodoDto completeTodo(Long id);
}
