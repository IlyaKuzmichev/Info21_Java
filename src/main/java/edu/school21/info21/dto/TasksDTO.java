package edu.school21.info21.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TasksDTO {
    private String title;
    private String parentTask;
    private int maxXp;
}
