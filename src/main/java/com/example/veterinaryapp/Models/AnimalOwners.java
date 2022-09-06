package com.example.veterinaryapp.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity //Entitiy sayılarak veri tabanı tarafından tanınabilmesi için kullanılan anatasyon.
@Data //@Getter @Setter ToString Equals and HashCode
@NoArgsConstructor //  Parametresiz constructor oluşturucu
@AllArgsConstructor // Sınıfa ait consturctor oluşturur.
@Builder
public class AnimalOwners {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Otomatik artan değerler için kullanıyoruz.
    @Column(name ="id")
    private long id;
    @Column (name ="ownerFullName")
    @NotBlank(message = "Name is Mandatory")
    private String ownerFullName;
    @Column (name ="ownerPhoneNumber")
    @NotBlank(message = "Phone Number is Mandatory")
    private String ownerPhoneNumber;
    @Column (name ="ownerEmail")
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is Mandatory")
    private String ownerEmail;


    @OneToMany//(mappedBy = "animalOwners" , cascade = CascadeType.ALL) // "Bir hayvan sahibine n adet hayvan tanımı yapılabilmeli"
    private List<Animals> animalsList = new ArrayList<>();

   /* public AnimalOwners(String ownerFullName, String ownerPhoneNumber, String ownerEmail) {
        this.ownerFullName = ownerFullName;
        this.ownerPhoneNumber = ownerPhoneNumber;
        this.ownerEmail = ownerEmail;

    }*/
}
