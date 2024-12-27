package com.example.systemegestionuniversitaire.Service;
;
import com.example.systemegestionuniversitaire.Entity.Etudiant;
import com.example.systemegestionuniversitaire.Repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EtudiantServiceImpl implements EtudiantService{
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Override
    public List<Etudiant> findAllEtudiants() {
        return etudiantRepository.findAll() ;
    }
}
