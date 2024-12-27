package com.example.systemegestionuniversitaire.Dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FiliereDto {
    @NotEmpty(message = "nom obligatoire")
    private String nom;
}
