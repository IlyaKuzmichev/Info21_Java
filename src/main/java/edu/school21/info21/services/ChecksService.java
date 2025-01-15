package edu.school21.info21.services;

import edu.school21.info21.dto.ChecksDTO;
import edu.school21.info21.mappers.ChecksMapper;
import edu.school21.info21.model.Checks;
import edu.school21.info21.repositories.ChecksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChecksService {

    private final ChecksRepository checksRepository;
    private final ChecksMapper checksMapper;

    @Autowired
    public ChecksService(ChecksRepository checksRepository, ChecksMapper checksMapper) {
        this.checksRepository = checksRepository;
        this.checksMapper = checksMapper;
    }

    public List<ChecksDTO> getAllChecks() {
        List<Checks> checksList = checksRepository.findAll();
        List<ChecksDTO> checksDTOList = new ArrayList<>();

        for (Checks check : checksList) {
            checksDTOList.add(checksMapper.toDTO(check));
        }

        return checksDTOList;
    }
}
