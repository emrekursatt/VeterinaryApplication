package com.example.veterinaryapp.services;

import com.example.veterinaryapp.models.Animals;
import com.example.veterinaryapp.repository.IAnimalsRepository;
import com.example.veterinaryapp.services.interfeace.IAnimalsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service// Bir beaninin business katmanında çalışacak bir bean olduğunu belirtir.
@AllArgsConstructor
@Transactional
public class AnimalsServiceImpl implements IAnimalsService {

    @Autowired
    private final IAnimalsRepository iAnimalsRepository;

    @Override
    public List<Animals> findAll() {
        return iAnimalsRepository.findAll();
    }

    @Override
    public void save(Animals animals) {
        iAnimalsRepository.save(animals);

    }

    @Override
    public Optional<Animals> findById(Long id) {
        return iAnimalsRepository.findById(id);
    }

    @Override
    @Secured(value = "ROLE_ADMIN")
    public void delete(Long id) {
        iAnimalsRepository.deleteById(id);
    }

    @Override
    public List<Animals> findByAnimalName(String animalName) {
        return iAnimalsRepository.findByAnimalName(animalName);
    }

    @Override
    public Long totalAnimals() {
        Long count = iAnimalsRepository.count();
        return count;
    }
}
