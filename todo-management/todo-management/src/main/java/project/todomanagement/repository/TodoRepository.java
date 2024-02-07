package project.todomanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.todomanagement.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo,Long> {
}
