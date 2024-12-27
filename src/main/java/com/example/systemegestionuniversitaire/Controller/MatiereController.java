package com.example.systemegestionuniversitaire.Controller;

import com.example.systemegestionuniversitaire.Dto.FiliereDto;
import com.example.systemegestionuniversitaire.Dto.MatiereDto;
import com.example.systemegestionuniversitaire.Entity.Filiere;
import com.example.systemegestionuniversitaire.Entity.Matiere;
import com.example.systemegestionuniversitaire.Repository.MatiereRepository;
import com.example.systemegestionuniversitaire.Service.MatiereService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/matieres")
public class MatiereController {
    @Autowired
    private MatiereService matiereService;
    @Autowired
    private MatiereRepository matiereRepository;
    @GetMapping({"", "/", "/index"})
    public String showMatiereList(Model model) {
        List<Matiere> matieres = matiereService.findAllMatieres();
        model.addAttribute("matieres", matieres);
        return "matieres/index";
    }
    @GetMapping("/ajouter")
    public String showAjouterMatiere(Model model) {
        MatiereDto matiereDto = new MatiereDto();

        model.addAttribute("matiereDto", matiereDto);
        return "matieres/ajouterMatiere";
    }
    @PostMapping("/ajouter")
    public String ajouterMatiere(@Valid @ModelAttribute("matiereDto") MatiereDto matiereDto, BindingResult result) {
        if (result.hasErrors()) {
            return "matieres/ajouterMatiere";
        }

        Matiere matiere = new Matiere();

        matiere.setNom(matiereDto.getNom());


        matiere.setDateCreation(new Date());
        matiereRepository.save(matiere);

        return "redirect:/matieres?success";
    }
    @GetMapping("/modifier")
    public String showEditPage(Model model, @RequestParam Long id) {
        try {
           Matiere matiere =matiereRepository.findById(id).get();
            model.addAttribute("matiere", matiere);
           MatiereDto matiereDto = new MatiereDto();
            matiereDto.setNom(matiere.getNom());
            model.addAttribute("matiereDto", matiereDto);

        }
        catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/matieres";

        }
        return "matieres/modifierMatiere";
    }

    @PostMapping("/modifier")
    public String updateMatiere(Model model,
                                @RequestParam Long id,
                                @Valid @ModelAttribute MatiereDto matiereDto
            , BindingResult result) {


        try {
            Matiere matiere = matiereRepository.findById(id).orElse(null);
            if (matiere == null) {
                return "redirect:/matieres";
            }

            model.addAttribute("matiere", matiere);
            if (result.hasErrors()) {
                return "matieres/modifierMatiere";
            }
            matiere.setNom(matiereDto.getNom());
            matiere.setDateCreation(new Date());

           matiereRepository.save(matiere);


        }catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());

        }

        return "redirect:/matieres";






    }
    @GetMapping("/supprimer")
    public String supprimerMatiere(@RequestParam Long id){
        try {
            Matiere matiere = matiereRepository.findById(id).get();
            matiereRepository.delete(matiere);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return "redirect:/matieres";
    }


}
