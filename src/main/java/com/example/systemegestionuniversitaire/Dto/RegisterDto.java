package com.example.systemegestionuniversitaire.Dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDto {
    @NotEmpty
    @Email
    private String email;
    @Size(min = 6,message = "la longeure du mot de passe est d'au moins 6 caractères")
    private String mdp;
    @NotEmpty
    private String confirmMdp;


}
