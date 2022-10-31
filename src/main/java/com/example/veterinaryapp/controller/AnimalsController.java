package com.example.veterinaryapp.Controller;

import com.example.veterinaryapp.models.AnimalOwners;
import com.example.veterinaryapp.models.Animals;
import com.example.veterinaryapp.services.interfeace.IAnimalOwnersService;
import com.example.veterinaryapp.services.interfeace.IAnimalsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/animal")
public class animalsController {


    @Autowired
    private final IAnimalsService iAnimalsService;
    @Autowired
    private final IAnimalOwnersService iAnimalOwnersService;


    @GetMapping("/animals")
    public String getAllAnimals(Model theModel){
        List<Animals> animalsList = iAnimalsService.findAll();
        theModel.addAttribute("animalsList" , animalsList);
        return "animal/animals";
    }

    @GetMapping("/create")
    public String createAnimalPage(@ModelAttribute ("animals") Animals animals , ModelMap modelMap){

        List<AnimalOwners> animalOwnersList = iAnimalOwnersService.findAll();

        boolean animalOwnersListEmpty = animalOwnersList.isEmpty();
        modelMap.addAttribute("animalOwnersList" , animalOwnersList);
        if(animalOwnersListEmpty){
            modelMap.addAttribute("ownerNotFound" , " Owner Not Found");

        }
        return "animal/create";

    }

    @PostMapping("/create")
    public String createAnimal(@ModelAttribute("pet") @Valid Animals animals , BindingResult bindingResult , RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "animal/create";
        }

        iAnimalsService.save(animals);
        redirectAttributes.addFlashAttribute("animals" , animals);
        redirectAttributes.addFlashAttribute("successfullyCreateAnimal" , "Successfully Create Animal");
        return  "redirect:/animal/animals";

    }


    @GetMapping("/update/{id}")
    public String updateAnimalsPage(@PathVariable Long id , ModelMap modelMap , RedirectAttributes redirectAttributes){

        List<AnimalOwners> animalOwnersList = iAnimalOwnersService.findAll();
        modelMap.addAttribute("animalOwnersList" , animalOwnersList);

        Optional<Animals> optionalAnimals = iAnimalsService.findById(id);

        if (optionalAnimals.isPresent()){
            Animals animals = optionalAnimals.get();
            modelMap.addAttribute("animals" , animals);
            return "animal/update";
        }
        redirectAttributes.addFlashAttribute("notFoundAnimals" ,"Not Found Animal" );
        return "redirect:/animal/animals";
    }

    @PostMapping("/update/{id}")
    public String updateAnimal(@ModelAttribute @Valid Animals animals , BindingResult bindingResult , RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            return "animal/animals";
        }

        iAnimalsService.save(animals);
        redirectAttributes.addFlashAttribute("successfullyUpdatedAnimal", animals.getAnimalName() + "Successfully Updated Animal");
        return "redirect:/animal/animals";

    }

    @GetMapping("/delete/{id}")
    public String deleteAnimal(@PathVariable Long id , RedirectAttributes redirectAttributes){
        iAnimalsService.delete(id);
        redirectAttributes.addFlashAttribute("successfullyDeletedAnimal" , "Successfully Deleted Animal");
        return "redirect:/animal/animals";
    }
   @GetMapping("/search")
   public String searchAnimalPage(@RequestParam(required = false) String animalName , ModelMap modelMap){

       List<Animals> animalsList = iAnimalsService.findByAnimalName(animalName);

       if (animalName == null ) {
            return "animal/search";
        }


        if(animalsList.isEmpty()){
            modelMap.addAttribute("notFoundAnimal" , animalName + "Not Found Animal");
            return "animal/search";
        }
        modelMap.addAttribute("animalFound" , animalName + "Animal Found");
        modelMap.addAttribute("animalsList" , animalsList);
        return "animal/animals";
   }
}




