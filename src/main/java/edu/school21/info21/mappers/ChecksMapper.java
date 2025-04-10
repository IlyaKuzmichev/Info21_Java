package edu.school21.info21.mappers;

import edu.school21.info21.dto.ChecksDTO;
import edu.school21.info21.model.Checks;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ChecksMapper {
    public ChecksDTO toDTO(Checks check) {
        return new ChecksDTO(
            check.getId(),
            check.getPeer(),
            check.getTask(),
            check.getDate().toString()
        );
    }

    public Checks toEntity(ChecksDTO dto) {
        Checks check = new Checks();
        check.setPeer(dto.getPeer());
        check.setTask(dto.getTask());
        check.setDate(LocalDate.parse(dto.getDate()));
        return check;
    }
}
