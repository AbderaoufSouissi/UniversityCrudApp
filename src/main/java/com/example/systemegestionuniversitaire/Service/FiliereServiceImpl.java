package com.example.systemegestionuniversitaire.Service;

import com.example.systemegestionuniversitaire.Entity.Filiere;
import com.example.systemegestionuniversitaire.Repository.FiliereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FiliereServiceImpl implements FiliereService {
    @Autowired
    FiliereRepository filiereRepository;
    @Override
    public List<Filiere> findAllFilieres() {
        return filiereRepository.findAll();
    }
}
