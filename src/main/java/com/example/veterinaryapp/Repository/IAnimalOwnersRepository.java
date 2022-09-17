package com.example.veterinaryapp.Repository;

import com.example.veterinaryapp.Models.AnimalOwners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository //Beanin dao ya da persistence sınıfı olduğunu belirtmek için kullanılır.
public interface IAnimalOwnersRepository extends JpaRepository<AnimalOwners , Long> {
    @Query("select a from AnimalOwners a where a.ownerEmail = ?1")
    boolean existEmail(String ownerEmail);

    @Query("select a from AnimalOwners a where a.ownerFullName = ?1")
    List<AnimalOwners> findByOwnerName(String ownerFullName);



}