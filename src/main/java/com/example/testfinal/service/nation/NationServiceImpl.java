package com.example.testfinal.service.nation;

import com.example.testfinal.model.Nation;
import com.example.testfinal.repository.NationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NationServiceImpl implements NationService{
    @Autowired
    private NationRepository nationRepository;
    @Override
    public List<Nation> findAll() {
        return nationRepository.findAll();
    }

    @Override
    public Nation findById(Long id) {
        return nationRepository.findById(id).get();
    }

    @Override
    public void save(Nation nation) {
        nationRepository.save(nation);
    }

    @Override
    public void delete(Long id) {
        nationRepository.deleteById(id);
    }
}
