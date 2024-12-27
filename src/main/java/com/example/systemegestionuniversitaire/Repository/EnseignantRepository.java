package com.example.systemegestionuniversitaire.Repository;

import com.example.systemegestionuniversitaire.Entity.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
}
