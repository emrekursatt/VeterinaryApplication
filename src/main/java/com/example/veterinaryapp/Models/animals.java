package com.example.veterinaryapp.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity //Entitiy sayılarak veri tabanı tarafından tanınabilmesi için kullanılan anatasyon.
@Data //@Getter @Setter ToString Equals and HashCode
@NoArgsConstructor //  Parametresiz constructor oluşturucu
@Builder
@AllArgsConstructor
public class Animals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Otomatik artan değerler için kullanıyoruz.
    @Column(name = "id")
    private long id;

    @Column(name = "animalName")
    @NotBlank(message = "Animal Name is Mandatory")
    private String animalName;

    @Column(name = "animalBreed")
    @NotBlank(message = "Animal Breed is Mandatory")
    private String animalBreed;

    @Column(name = "animalType")
    @NotBlank(message = "Animal Type is Mandatory")
    private String animalType;

    @Column(name = "animalDescription")
    @NotBlank(message = "Animal Description is Mandatory")
    private String animalDescription;

    @Column(name = "animalAge")
    //@NotBlank(message = "Animal Age is Mandatory")
    private double animalAge;

    @ManyToOne // "Bir hayvan sahibine n adet hayvan tanımı yapılabilmeli"
    private AnimalOwners animalOwners;


}

