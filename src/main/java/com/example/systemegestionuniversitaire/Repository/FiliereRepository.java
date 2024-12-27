package com.example.systemegestionuniversitaire.Repository;


import com.example.systemegestionuniversitaire.Entity.Filiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {
}
