package edu.school21.info21.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "checks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Checks {
    @Id
    private int id;
    private String peer;
    private String task;
    private LocalDate date;
}
