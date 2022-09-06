package com.example.veterinaryapp.Services;

import com.example.veterinaryapp.Models.AnimalOwners;
import com.example.veterinaryapp.Repository.IAnimalOwnersRepository;
import com.example.veterinaryapp.Services.Interfeace.IAnimalOwnersService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service // Bir beaninin business katmanında çalışacak bir bean olduğunu belirtir.
@AllArgsConstructor
@Transactional
public class AnimalOwnersServiceImpl implements IAnimalOwnersService {

    @Autowired
    private final IAnimalOwnersRepository iAnimalOwnersRepository;


    @Override
    public List<AnimalOwners> findAll() {
        return iAnimalOwnersRepository.findAll();
    }



    @Override
    public void save(AnimalOwners animalOwners) {
        iAnimalOwnersRepository.save(animalOwners);
    }



    @Override
    public boolean isAdmin(Authentication authentication) {
        return authentication
                .getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE ADMIN"));


    }


    @Override
    public Long totalOwners() {
        Long count = iAnimalOwnersRepository.count();
        return count;
    }


    @Override
    public Optional<AnimalOwners> findById(Long id) {

        return iAnimalOwnersRepository.findById(id);
    }

    @Override
    @Secured(value= "ROLE_ADMIN")
    public void delete(Long id) {
        iAnimalOwnersRepository.deleteById(id);
    }


    @Override
    public List<AnimalOwners> findByOwnerName(String ownerFullName) {
        return iAnimalOwnersRepository.findByOwnerName(ownerFullName);
    }

    @Override
    public boolean existEmail(String ownerMail) {
        return iAnimalOwnersRepository.existEmail(ownerMail);
    }




}