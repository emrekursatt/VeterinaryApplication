package com.example.veterinaryapp.services.interfeace;

import com.example.veterinaryapp.models.Animals;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Bir beaninin business katmanında çalışacak bir bean olduğunu belirtir.
public interface IAnimalsService {
    List<Animals> findAll();

    void save(Animals animals);

    Optional<Animals> findById(Long id);

    void delete(Long id);

    List<Animals> findByAnimalName(String animalName);

    Long totalAnimals();
}
