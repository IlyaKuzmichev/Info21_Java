package edu.school21.info21.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChecksDTO {
    private int id;
    private String peer;
    private String task;
    private String date;
}
