package com.example.veterinaryapp.Controller;

import com.example.veterinaryapp.Models.Role;
import com.example.veterinaryapp.Models.User;
import com.example.veterinaryapp.Services.Interfeace.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class RegisterController {
    @Autowired
    IUserService iUserService;

    @PostMapping("/register")
    public String createUser(@ModelAttribute("createUser") @Valid  User user, BindingResult bindingResult, Model theModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        String username = user.getUsername();
        String password = user.getPassword();

        Optional<User> optionalUser = (iUserService.findUserByUsername(username));
        if (optionalUser.isPresent()) {
            theModel.addAttribute("alreadyExistsUser", "Username is used");
            return "register";

        }

        iUserService.save(user);
        iUserService.autoLogin(httpServletRequest, username, password);
        List<String> userRoleNames = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        return "redirect:/index";
    }

}
