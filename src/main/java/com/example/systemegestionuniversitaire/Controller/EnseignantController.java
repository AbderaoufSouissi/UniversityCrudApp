package com.example.systemegestionuniversitaire.Controller;

import com.example.systemegestionuniversitaire.Dto.EnseignantDto;
import com.example.systemegestionuniversitaire.Entity.Enseignant;
import com.example.systemegestionuniversitaire.Repository.EnseignantRepository;
import com.example.systemegestionuniversitaire.Service.EnseignantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/enseignants")
public class EnseignantController {
    @Autowired
    private EnseignantService enseignantService;
    @Autowired
    private EnseignantRepository enseignantRepository;

    @GetMapping({"", "/", "/index"})
    public String showEnseignantList(Model model) {
        List<Enseignant> enseignants = enseignantService.findAllEnseignants();
        model.addAttribute("enseignants", enseignants);
        return "enseignants/index";
    }

    @GetMapping("/ajouter")
    public String showAjouterEnseignant(Model model) {
        EnseignantDto enseignantDto = new EnseignantDto();

        model.addAttribute("enseignantDto", enseignantDto);
        return "enseignants/ajouterEnseignant";
    }

    @PostMapping("/ajouter")
    public String ajouterEnseignant(@Valid @ModelAttribute("enseignantDto") EnseignantDto enseignantDto, BindingResult result) {
        if (result.hasErrors()) {
            return "enseignants/ajouterLivre";
        }

        Enseignant enseignant = new Enseignant();
        enseignant.setPrenom(enseignantDto.getPrenom());
        enseignant.setNom(enseignantDto.getNom());
        enseignant.setEmail(enseignantDto.getEmail());

        enseignant.setDateCreation(new Date());
        enseignantRepository.save(enseignant);

        return "redirect:/enseignants?success";
    }
    @GetMapping("/modifier")
    public String showEditPage(Model model, @RequestParam Long id) {
        try {
            Enseignant enseignant=enseignantRepository.findById(id).get();
            model.addAttribute("enseignant", enseignant);
            EnseignantDto enseignantDto = new EnseignantDto();
            enseignantDto.setNom(enseignant.getNom());
            enseignantDto.setPrenom(enseignant.getPrenom());
            enseignantDto.setEmail(enseignant.getEmail());;
            model.addAttribute("enseignantDto", enseignantDto);

        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/enseignants";

        }
        return "enseignants/modifierEnseignant";
    }



    @PostMapping("/modifier")
    public String updateEnseignant(Model model,
                                   @RequestParam Long id,
                                   @Valid @ModelAttribute EnseignantDto enseignantDto
            , BindingResult result) {


        try {
            Enseignant enseignant = enseignantRepository.findById(id).orElse(null);
            if (enseignant == null) {
                return "redirect:/enseignants";
            }

            model.addAttribute("enseignant", enseignant);
            if (result.hasErrors()) {
                return "enseignants/modifierEnseignant";
            }
            enseignant.setNom(enseignantDto.getNom());
            enseignant.setPrenom(enseignantDto.getPrenom());
            enseignant.setEmail(enseignantDto.getEmail());
            enseignant.setDateCreation(new Date());

            enseignantRepository.save(enseignant);


        }catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());

        }

        return "redirect:/enseignants";






    }





    @GetMapping("/supprimer")
    public String supprimerEnseignant(@RequestParam Long id){
        try {
            Enseignant enseignant = enseignantRepository.findById(id).get();
            enseignantRepository.delete(enseignant);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/enseignants";
    }
}




