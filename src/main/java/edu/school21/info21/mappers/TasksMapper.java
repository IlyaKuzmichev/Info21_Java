package edu.school21.info21.mappers;

import edu.school21.info21.dto.TasksDTO;
import edu.school21.info21.model.Tasks;
import org.springframework.stereotype.Component;

@Component
public class TasksMapper {

    public Tasks toEntity(TasksDTO dto) {
        return new Tasks(dto.getTitle(), dto.getParentTask(), dto.getMaxXp());
    }

    public TasksDTO toDTO(Tasks task) {
        return new TasksDTO(task.getTitle(), task.getParentTask(), task.getMaxXp());
    }
}
