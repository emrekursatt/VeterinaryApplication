package com.example.veterinaryapp.Services.Interfeace;

import com.example.veterinaryapp.Models.User;

import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public interface IUserService {

    Optional<User> findUserByUsername(String username);

    void save(User user);


    String findUsernameOfLoggedInUser();

    void autoLogin(HttpServletRequest httpServletRequest, String username, String password);
}
