package edu.school21.info21.services;

import edu.school21.info21.dto.TasksDTO;
import edu.school21.info21.mappers.TasksMapper;
import edu.school21.info21.model.Tasks;
import edu.school21.info21.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TasksService {

    private final TasksRepository tasksRepository;
    private final TasksMapper tasksMapper;

    @Autowired
    public TasksService(TasksRepository tasksRepository, TasksMapper tasksMapper) {
        this.tasksRepository = tasksRepository;
        this.tasksMapper = tasksMapper;
    }

    public List<TasksDTO> getAllTasks() {
        List<Tasks> tasksList = tasksRepository.findAll();
        List<TasksDTO> tasksDTOList = new ArrayList<>();

        for (Tasks task : tasksList) {
            tasksDTOList.add(tasksMapper.toDTO(task));
        }

        return tasksDTOList;
    }
}
