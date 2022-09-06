package com.example.veterinaryapp.Repository;

import com.example.veterinaryapp.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository //Bean'in dao ya da persistence sınıfı olduğunu belirtmek için kullanılır.
public interface IUserRepository  extends JpaRepository<User, Long> {

    Optional<User> findUserByUsername(String username);

}
