package com.example.veterinaryapp.Config;

import com.example.veterinaryapp.Models.Role;
import com.example.veterinaryapp.Models.User;
import com.example.veterinaryapp.Repository.IRoleRepository;
import com.example.veterinaryapp.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class dataLoader implements ApplicationRunner {

    @Autowired
    private IUserRepository iUserRepository;
    @Autowired
    private IRoleRepository iRoleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public dataLoader(IUserRepository iUserRepository, IRoleRepository iRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.iUserRepository = iUserRepository;
        this.iRoleRepository = iRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        // Added roles.

        Role userRole = new Role("ROLE_USER");
        Role adminRole = new Role("ROLE_ADMIN");
        iRoleRepository.save(userRole);
        iRoleRepository.save(adminRole);

        // Added admin user.

        String adminPassword = bCryptPasswordEncoder.encode("admin");
        User adminUser = new User("admin", adminPassword, true);
        iUserRepository.save(adminUser);

        // Admin user has been assigned the admin role.

        Set<Role> adminRoleSet = new HashSet<>(Arrays.asList(adminRole, userRole));
        User admin = iUserRepository.findUserByUsername("admin").get();

        admin.setRoles(adminRoleSet);
        iUserRepository.saveAndFlush(admin);



        // Added  user.

        String userPassword = bCryptPasswordEncoder.encode("user");
        User userUser = new User("user", userPassword, true);
        iUserRepository.save(userUser);

        // User has been assigned the  role.

        Set<Role> userRoleSet = new HashSet<>(Arrays.asList(userRole));
        User user = iUserRepository.findUserByUsername("user").get();

        user.setRoles(userRoleSet);
        iUserRepository.saveAndFlush(user);
    }
}
