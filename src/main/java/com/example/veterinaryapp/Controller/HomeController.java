package com.example.veterinaryapp.Controller;

import com.example.veterinaryapp.Models.User;
import com.example.veterinaryapp.Services.Interfeace.IAnimalOwnersService;
import com.example.veterinaryapp.Services.Interfeace.IAnimalsService;
import com.example.veterinaryapp.Services.Interfeace.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
public class HomeController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IAnimalOwnersService iAnimalOwnersService;

    @Autowired
    private IAnimalsService iAnimalsService;

    @ModelAttribute("user")
    public User user() {
        return new User();
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }


    @GetMapping("/index")
    public String indexPage2() {
        return "index";
    }


    @GetMapping("/register")
    public String registerShowPage(@ModelAttribute("createUser") User user) {
        return "register";
    }



    @GetMapping({"/dashboard" , "/dashboard.html"})
    public String dashboardPage(Model theModel) {

        Long totalOwners = iAnimalOwnersService.totalOwners();
        Long totanAnimals = iAnimalsService.totalAnimals();

        theModel.addAttribute("totalOwners", totalOwners);
        theModel.addAttribute("totanAnimals", totanAnimals);

        return "dashboard";
    }


    @GetMapping({"/login", "/login.html"})
    public String loginPage() {

        return "login";
    }

}
