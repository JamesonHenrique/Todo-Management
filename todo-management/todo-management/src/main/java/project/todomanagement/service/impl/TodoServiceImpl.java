package project.todomanagement.service.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.todomanagement.dto.TodoDto;
import project.todomanagement.entity.Todo;
import project.todomanagement.exception.ResourceNotFoundException;
import project.todomanagement.repository.TodoRepository;
import project.todomanagement.service.TodoService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service


public class TodoServiceImpl implements TodoService {
    private TodoRepository todoRepository;

    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);

        Todo savedTodo = todoRepository.save(todo);
        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);
        return savedTodoDto;
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Todo not found with id" + id)
        );
        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public void removeTodo(Long Id) {
        Todo todo = todoRepository.findById(Id).orElseThrow(
                () -> new ResourceNotFoundException("Todo not found with id" + Id));

        todoRepository.deleteById(Id);
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Todo not found with id" + id));

        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.getCompleted());

        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Todo not found with id" + id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedtodo = todoRepository.save(todo);
        return modelMapper.map(updatedtodo,TodoDto.class);
    }

}



