package com.example.systemegestionuniversitaire.Repository;

import com.example.systemegestionuniversitaire.Entity.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {

}
