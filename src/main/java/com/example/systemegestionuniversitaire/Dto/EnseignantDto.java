package com.example.systemegestionuniversitaire.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnseignantDto {
    @NotEmpty(message = "prenom obligatoire")
    private String prenom;
    @NotEmpty(message = "nom obligatoire")
    private String nom;
    @NotEmpty
    @Email
    private String email;

}
