package com.example.veterinaryapp.services;



import com.example.veterinaryapp.models.Role;
import com.example.veterinaryapp.models.User;
import com.example.veterinaryapp.repository.IRoleRepository;
import com.example.veterinaryapp.repository.IUserRepository;
import com.example.veterinaryapp.services.interfeace.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service // Bir beaninin business katmanında çalışacak bir bean olduğunu belirtir.
@AllArgsConstructor
public class UserServiceImpl implements IUserService {



    @Autowired
    private IUserRepository iuserRepository;

    @Autowired
    private IRoleRepository iRoleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Optional<User> findUserByUsername(String username) {
        return iuserRepository.findUserByUsername(username);
    }

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        Role userRole = iRoleRepository.findRoleByName("ROLE_USER");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        iuserRepository.save(user);
    }

    @Override
    public String findUsernameOfLoggedInUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public void autoLogin(HttpServletRequest httpServletRequest, String username, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        authToken.setDetails(new WebAuthenticationDetails(httpServletRequest));
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


}
