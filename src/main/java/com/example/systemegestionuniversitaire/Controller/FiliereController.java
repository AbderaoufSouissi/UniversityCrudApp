package com.example.systemegestionuniversitaire.Controller;

import com.example.systemegestionuniversitaire.Dto.FiliereDto;
import com.example.systemegestionuniversitaire.Entity.Filiere;
import com.example.systemegestionuniversitaire.Repository.FiliereRepository;
import com.example.systemegestionuniversitaire.Service.FiliereService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("/filieres")
public class FiliereController {
    @Autowired
    private FiliereService filiereService;
    @Autowired
    private FiliereRepository filiereRepository;

    @GetMapping({"", "/", "/index"})
    public String showFiliereList(Model model) {
        List<Filiere> filieres = filiereService.findAllFilieres();
        model.addAttribute("filieres", filieres);
        return "filieres/index";
    }

    @GetMapping("/ajouter")
    public String showAjouterFiliere(Model model) {
        FiliereDto filiereDto = new FiliereDto();

        model.addAttribute("filiereDto", filiereDto);
        return "filieres/ajouterFiliere";
    }

    @PostMapping("/ajouter")
    public String ajouterFiliere(@Valid @ModelAttribute("filiereDto") FiliereDto filiereDto, BindingResult result) {
        if (result.hasErrors()) {
            return "filieres/ajouterFiliere";
        }

        Filiere filiere = new Filiere();

        filiere.setNom(filiereDto.getNom());


        filiere.setDateCreation(new Date());
        filiereRepository.save(filiere);

        return "redirect:/filieres?success";
    }
    @GetMapping("/modifier")
    public String showEditPage(Model model, @RequestParam Long id) {
        try {
            Filiere filiere =filiereRepository.findById(id).get();
            model.addAttribute("filiere", filiere);
            FiliereDto filiereDto = new FiliereDto();
            filiereDto.setNom(filiere.getNom());
            model.addAttribute("filiereDto", filiereDto);

        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/filieres";

        }
        return "filieres/modifierFiliere";
    }



    @PostMapping("/modifier")
    public String updateFiliere(Model model,
                                @RequestParam Long id,
                                @Valid @ModelAttribute FiliereDto filiereDto
            , BindingResult result) {


        try {
            Filiere filiere = filiereRepository.findById(id).orElse(null);
            if (filiere == null) {
                return "redirect:/filieres";
            }

            model.addAttribute("filiere", filiere);
            if (result.hasErrors()) {
                return "filieres/modifierFiliere";
            }
            filiere.setNom(filiereDto.getNom());
           ;
            filiere.setDateCreation(new Date());

            filiereRepository.save(filiere);


        }catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());

        }

        return "redirect:/filieres";






    }





    @GetMapping("/supprimer")
    public String supprimerFiliere(@RequestParam Long id){
        try {
            Filiere filiere = filiereRepository.findById(id).get();
            filiereRepository.delete(filiere);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/filieres";
    }
}
