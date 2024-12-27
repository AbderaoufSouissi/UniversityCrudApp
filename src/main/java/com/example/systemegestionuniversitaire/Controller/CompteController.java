package com.example.systemegestionuniversitaire.Controller;



import com.example.systemegestionuniversitaire.Dto.RegisterDto;
import com.example.systemegestionuniversitaire.Entity.Utilisateur;
import com.example.systemegestionuniversitaire.Repository.UtilisateurRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;


@Controller
public class CompteController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        model.addAttribute("success", false);
        return "register";
    }
    @PostMapping("/register")
    public String register(
            @Valid @ModelAttribute RegisterDto registerDto,
            BindingResult result,
            Model model) {

        if (!registerDto.getMdp().equals(registerDto.getConfirmMdp())) {
            result.addError(
                    new FieldError(
                            "registerRequest",
                            "confirmMdp",
                            "Les deux mots de passe ne sont pas identiques")
            );
        }

        Utilisateur existingUser = utilisateurRepository.findByEmail(registerDto.getEmail());
        if (existingUser != null) {
            result.addError(
                    new FieldError(
                            "registerDto",
                            "email",
                            "Adresse mail existe déjà")
            );
        }

        if (result.hasErrors()) {
            return "register";
        }

        try {
            var bcryptEncoder = new BCryptPasswordEncoder();
            Utilisateur newUser = new Utilisateur();
            newUser.setEmail(registerDto.getEmail());
            newUser.setMdp(bcryptEncoder.encode(registerDto.getMdp()));

            newUser.setDateCreation(new Date());
            utilisateurRepository.save(newUser);

            model.addAttribute("registerDto", new RegisterDto());
            model.addAttribute("success", true);

        } catch (Exception ex) {
            result.addError(
                    new FieldError("registerDto", "prenom", ex.getMessage())
            );
        }

        return "register";
    }
}

