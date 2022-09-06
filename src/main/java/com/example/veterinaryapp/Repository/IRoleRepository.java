package com.example.veterinaryapp.Repository;

import com.example.veterinaryapp.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByName(String roleName);
}
