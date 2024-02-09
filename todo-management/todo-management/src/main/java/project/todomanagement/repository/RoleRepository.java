package project.todomanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.todomanagement.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
