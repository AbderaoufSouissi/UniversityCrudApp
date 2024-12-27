package com.example.systemegestionuniversitaire.Service;

import com.example.systemegestionuniversitaire.Entity.Matiere;
import com.example.systemegestionuniversitaire.Repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MatiereServiceImpl implements MatiereService{
    @Autowired
    private MatiereRepository matiereRepository;

    @Override
    public List<Matiere> findAllMatieres() {
        return matiereRepository.findAll();
    }
}
