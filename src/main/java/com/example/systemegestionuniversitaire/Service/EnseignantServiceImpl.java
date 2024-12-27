package com.example.systemegestionuniversitaire.Service;

import com.example.systemegestionuniversitaire.Entity.Enseignant;
import com.example.systemegestionuniversitaire.Repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EnseignantServiceImpl implements EnseignantService{
    @Autowired
    private EnseignantRepository enseignantRepository;
    @Override
    public List<Enseignant> findAllEnseignants() {
        return enseignantRepository.findAll() ;
    }
}
