package com.example.veterinaryapp.Repository;

import com.example.veterinaryapp.Models.Animals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //Beanin dao ya da persistence sınıfı olduğunu belirtmek için kullanılır.
public interface IAnimalsRepository extends JpaRepository<Animals,Long> {
    @Query("select a from Animals a where a.animalName = ?1")
    List<Animals> findByAnimalName(String animalName);



}
