package com.example.systemegestionuniversitaire.Service;

import com.example.systemegestionuniversitaire.Entity.Enseignant;
import com.example.systemegestionuniversitaire.Entity.Etudiant;

import java.util.List;

public interface EtudiantService {
    List<Etudiant> findAllEtudiants();
}
