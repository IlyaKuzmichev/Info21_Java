package edu.school21.info21.repositories;

import edu.school21.info21.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository<Tasks, String> {
}
