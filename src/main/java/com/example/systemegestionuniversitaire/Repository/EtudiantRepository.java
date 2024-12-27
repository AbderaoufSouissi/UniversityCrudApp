package com.example.systemegestionuniversitaire.Repository;

import com.example.systemegestionuniversitaire.Entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
}
