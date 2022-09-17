package com.example.veterinaryapp.Controller;

import com.example.veterinaryapp.Models.AnimalOwners;
import com.example.veterinaryapp.Services.Interfeace.IAnimalOwnersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller // Spring MVC  siniflarında kullanılır.
@RequiredArgsConstructor
@RequestMapping("/owner")
public class AnimalOwnersController {


    @Autowired
    private final IAnimalOwnersService iAnimalOwnersService;




    @GetMapping("/owners")
    public String allOwners(Model theModel) {
        List<AnimalOwners> animalOwnersList = iAnimalOwnersService.findAll();
        theModel.addAttribute("animalOwnersList", animalOwnersList);
        return "owner/owners";
    }

    @GetMapping("/createowner")
    public String createOwnerPage(@ModelAttribute("animalOwners") AnimalOwners animalOwners ,Model theModel) {

        return "owner/createowner";
    }

    @PostMapping("/createowner")
    public String createOwner(@Valid @ModelAttribute AnimalOwners animalOwners, BindingResult bindingResult, Model theModel, RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {

            return "owner/createowner";
        }

       String ownerMail = animalOwners.getOwnerEmail();
        boolean existMail = iAnimalOwnersService.existEmail(ownerMail);

        if (existMail) {
            theModel.addAttribute("existEmail", "Mail in Use");
            return "owner/createowner";
        }

        iAnimalOwnersService.save(animalOwners);
        redirectAttributes.addFlashAttribute("animalOwners", animalOwners);
        redirectAttributes.addFlashAttribute("petOwnerSuccessFullyAdded", "Pet Owner Successfully Added");
        return "redirect:/owner/owners";
    }


    @GetMapping("/update/{id}")
    public String uptadeAnimalOwnersPage(@PathVariable Long id, Model theModel , RedirectAttributes redirectAttributes) {



        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = iAnimalOwnersService.isAdmin(authentication);

        if (isAdmin) {
            theModel.addAttribute("admin", "admin");
        }

        Optional<AnimalOwners> animalOwners = iAnimalOwnersService.findById(id);

        if (animalOwners.isPresent()) {
            theModel.addAttribute("animalOwners", animalOwners);
            return "owner/update";
        }
        redirectAttributes.addFlashAttribute("petOwnerNotFound","Pet owner not found ");
        return "redirect:/owner/owners";
    }
    @PostMapping("/update/{id}")
    public String uptadeAnimalOwners( @PathVariable Long id, @Valid @ModelAttribute AnimalOwners animalOwners , BindingResult bindingResult , RedirectAttributes redirectAttributes){

        if (bindingResult.hasErrors()){
            return "owner/update";
        }

        iAnimalOwnersService.save(animalOwners);
        redirectAttributes.addFlashAttribute("updateSuccessfulAnimalOwners" , "Update Successful Animal Owners");
        return "redirect:/owner/owners";
    }

    @GetMapping("/delete/{id}")
    public String deleteAnimalsOwners(@PathVariable Long id, RedirectAttributes redirectAttributes){
        AnimalOwners animalOwners = iAnimalOwnersService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Animal Owner : " + id));
        iAnimalOwnersService.delete(id);
        redirectAttributes.addFlashAttribute("deleteSuccessfulAnimalOwners","Delete Successful Animal Owners");
        return "redirect:/owner/owners";
    }

    @GetMapping("/search")
    public String searchAnimalOwnersPage(@RequestParam (required = false) String ownerFullName , Model theModel){

        List<AnimalOwners> animalOwnersList = iAnimalOwnersService.findByOwnerName(ownerFullName);

        if(ownerFullName == null){
            return "owner/search";
        }



        if(animalOwnersList.isEmpty()){
            theModel.addAttribute("petOwnerNotFound" , ownerFullName + "Pet Owner Not Found");
            return "owner/search";
        }

        theModel.addAttribute("petOwnerFound" , "Pet Owner Found = " + ownerFullName);
        theModel.addAttribute("animalOwnersList",animalOwnersList);
        return "owner/owners";
    }

}



