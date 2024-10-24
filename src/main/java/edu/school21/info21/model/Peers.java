package edu.school21.info21.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "peers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Peers {
    @Id
    private String nickname;
    private LocalDate birthday;
}
