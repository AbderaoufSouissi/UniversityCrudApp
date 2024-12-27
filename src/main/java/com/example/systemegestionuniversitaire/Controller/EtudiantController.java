package com.example.systemegestionuniversitaire.Controller;

import com.example.systemegestionuniversitaire.Dto.EtudiantDto;
import com.example.systemegestionuniversitaire.Entity.Etudiant;
import com.example.systemegestionuniversitaire.Repository.EtudiantRepository;
import com.example.systemegestionuniversitaire.Service.EtudiantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/etudiants")
public class EtudiantController {
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private EtudiantRepository etudiantRepository;

    @GetMapping({"", "/", "/index"})
    public String showEtudiantList(Model model) {
        List<Etudiant> etudiants = etudiantService.findAllEtudiants();
        model.addAttribute("etudiants", etudiants);
        return "etudiants/index";
    }

    @GetMapping("/ajouter")
    public String showAjouterEtudiant(Model model) {
        EtudiantDto etudiantDto = new EtudiantDto() ;

        model.addAttribute("etudiantDto", etudiantDto);
        return "etudiants/ajouterEtudiant";
    }

    @PostMapping("/ajouter")
    public String ajouterEtudiant(@Valid @ModelAttribute("etudiantDto") EtudiantDto etudiantDto, BindingResult result) {
        if (result.hasErrors()) {
            return "etudiants/ajouterEtudiant";
        }

        Etudiant etudiant = new Etudiant();
        etudiant.setPrenom(etudiantDto.getPrenom());
        etudiant.setNom(etudiantDto.getNom());
        etudiant.setEmail(etudiantDto.getEmail());

        etudiant.setDateCreation(new Date());
        etudiantRepository.save(etudiant);

        return "redirect:/etudiants?success";
    }
    @GetMapping("/modifier")
    public String showEditPage(Model model, @RequestParam Long id) {
        try {
            Etudiant etudiant=etudiantRepository.findById(id).get();
            model.addAttribute("etudiant", etudiant);
            EtudiantDto etudiantDto = new EtudiantDto();
            etudiantDto.setNom(etudiant.getNom());
            etudiantDto.setPrenom(etudiant.getPrenom());
            etudiantDto.setEmail(etudiant.getEmail());;
            model.addAttribute("etudiantDto", etudiantDto);

        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/etudiants";

        }
        return "etudiants/modifierEtudiant";
    }



    @PostMapping("/modifier")
    public String updateEtudiant(Model model,
                                   @RequestParam Long id,
                                   @Valid @ModelAttribute EtudiantDto etudiantDto
            , BindingResult result) {


        try {
            Etudiant etudiant=etudiantRepository.findById(id).orElse(null);
            if (etudiant == null) {
                return "redirect:/etudiants";
            }

            model.addAttribute("etudiant", etudiant);
            if (result.hasErrors()) {
                return "etudiants/modifierEtudiant";
            }
            etudiant.setNom(etudiantDto.getNom());
            etudiant.setPrenom(etudiantDto.getPrenom());
            etudiant.setEmail(etudiantDto.getEmail());
            etudiant.setDateCreation(new Date());

            etudiantRepository.save(etudiant);


        }catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());

        }

        return "redirect:/etudiants";






    }





    @GetMapping("/supprimer")
    public String supprimerEtudiant(@RequestParam Long id){
        try {
            Etudiant etudiant = etudiantRepository.findById(id).get();
            etudiantRepository.delete(etudiant);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/etudiants";
    }
}
