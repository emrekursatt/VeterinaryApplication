package com.example.veterinaryapp.utils;

import com.example.veterinaryapp.models.AnimalOwners;
import com.example.veterinaryapp.models.Animals;
import com.example.veterinaryapp.repository.IAnimalOwnersRepository;
import com.example.veterinaryapp.repository.IAnimalsRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@AllArgsConstructor
public class InitializerRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitializerRunner.class);
    @Autowired
    private final IAnimalOwnersRepository iAnimalOwnersRepository;

    @Autowired
    private final IAnimalsRepository iAnimalsRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        iAnimalsRepository.deleteAll();
        iAnimalOwnersRepository.deleteAll();


        AnimalOwners animalOwners1 = iAnimalOwnersRepository.save(AnimalOwners.builder().ownerFullName("Emre Kürşat ÖZER").ownerPhoneNumber("05067467358").ownerEmail("emrekursatt@gmail.com").build());
        AnimalOwners animalOwners2 =iAnimalOwnersRepository.save(AnimalOwners.builder().ownerFullName("Burak Altuncu").ownerPhoneNumber("05067777777").ownerEmail("burakaltuncu@gmail.com").build());
        AnimalOwners animalOwners3 =iAnimalOwnersRepository.save(AnimalOwners.builder().ownerFullName("Samet Sevinç").ownerPhoneNumber("05066666666").ownerEmail("sametsevinc@gmail.com").build());

        Animals animals1 = iAnimalsRepository.save(Animals.builder().animalName("Kaju").animalAge(0.8).animalBreed("Pekinez").animalType("Köpek").animalDescription("Sakin Köpek").build());
        Animals animals2 = iAnimalsRepository.save(Animals.builder().animalName("Boncuk").animalAge(0.9).animalBreed("British").animalType("Kedi").animalDescription("Sakin Kedi").build());
        Animals animals3 = iAnimalsRepository.save(Animals.builder().animalName("Kara").animalAge(1.1).animalBreed("Papağan").animalType("Kuş").animalDescription("Sakin Kuş").build());
        Animals animals4 = iAnimalsRepository.save(Animals.builder().animalName("Beyaz").animalAge(0.3).animalBreed("Levrek").animalType("Balık").animalDescription("Sakin Balık").build());

        animals1.setAnimalOwners(animalOwners1);
        animals2.setAnimalOwners(animalOwners2);
        animals3.setAnimalOwners(animalOwners3);
        animals4.setAnimalOwners(animalOwners2);

        logger.info("All test data saved...");
    }
}
