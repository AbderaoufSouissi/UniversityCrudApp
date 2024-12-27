package com.example.systemegestionuniversitaire.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatiereDto {
    @NotEmpty(message = "nom obligatoire")
    private String nom;
}
