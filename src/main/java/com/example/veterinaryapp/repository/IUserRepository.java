package com.example.veterinaryapp.repository;

import com.example.veterinaryapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository //Bean'in dao ya da persistence sınıfı olduğunu belirtmek için kullanılır.
public interface IUserRepository  extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

}
