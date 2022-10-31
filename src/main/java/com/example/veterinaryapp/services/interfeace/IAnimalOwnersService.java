package com.example.veterinaryapp.services.interfeace;

import com.example.veterinaryapp.models.AnimalOwners;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Bir beaninin business katmanında çalışacak bir bean olduğunu belirtir.
public interface IAnimalOwnersService {
    List<AnimalOwners> findAll();


    void save(AnimalOwners animalOwners);


    Optional<AnimalOwners> findById(Long id);


    void delete(Long id);


    List<AnimalOwners> findByOwnerName(String ownerFullName);


    boolean existEmail(String ownerMail);

    boolean isAdmin(Authentication authentication);


    Long totalOwners();
}
