package karol.spring.petclinic.boostrapData;

import karol.spring.petclinic.models.*;
import karol.spring.petclinic.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * @author Karol Wlazło
 * pet-clinic
 */

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerRepository ownerRepository;
    private final SpecialityRepository specialityRepository;
    private final VetRepository vetRepository;
    private final PetTypeRepository petTypeRepository;
    private final PetRepository petRepository;

    public DataLoader(OwnerRepository ownerRepository, SpecialityRepository specialityRepository, VetRepository vetRepository, PetTypeRepository petTypeRepository, PetRepository petRepository) {
        this.ownerRepository = ownerRepository;
        this.specialityRepository = specialityRepository;
        this.vetRepository = vetRepository;
        this.petTypeRepository = petTypeRepository;
        this.petRepository = petRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner ownerKarol = new Owner();
        ownerKarol.setFirstName("Karol");
        ownerKarol.setLastName("Wlazło");
        ownerKarol.setCity("Balice");
        ownerKarol.getPets().add(new Pet());

        ownerRepository.save(ownerKarol);
        PetType dog = new PetType();
        dog.setName("Pies");

        petTypeRepository.save(dog);

        Pet roki = new Pet();
        roki.setName("Roki");
        roki.setPetType(dog);
        roki.setOwner(ownerKarol);

        Pet borys = new Pet();
        borys.setName("Borys");
        borys.setPetType(dog);
        borys.setOwner(ownerKarol);

        petRepository.save(roki);
        petRepository.save(borys);
        //ownerRepository.saveAll(loadOwners());
        vetRepository.saveAll(loadVets());
    }

    public List<Owner> loadOwners(){

        List<Owner> owners = new ArrayList<>();

        Owner ownerKarol = new Owner();
        ownerKarol.setFirstName("Karol");
        ownerKarol.setLastName("Wlazło");
        ownerKarol.setCity("Balice");
        owners.add(ownerKarol);
        ownerKarol.getPets().add(new Pet());

        PetType dog = new PetType();
        dog.setName("Pies");

        petTypeRepository.save(dog);

        Pet roki = new Pet();
        roki.setName("Roki");
        roki.setPetType(dog);
        roki.setOwner(ownerKarol);

        petRepository.save(roki);
        return owners;
    }


    public List<Vet> loadVets(){
        // loading specialities
        List<Speciality> specialities = new ArrayList<>();

        Speciality surgery = new Speciality();
        surgery.setName("Surgery");
        specialities.add(surgery);

        Speciality radiology = new Speciality();
        radiology.setName("Radiology");
        specialities.add(radiology);

        Speciality cardiology = new Speciality();
        cardiology.setName("Cardiology");
        specialities.add(cardiology);

        Speciality oncology = new Speciality();
        oncology.setName("Oncology");
        specialities.add(oncology);

        specialityRepository.saveAll(specialities);

        ///
        List<Vet> vets = new ArrayList<>();

        Vet vetKatarzyna = new Vet();
        vetKatarzyna.setFirstName("Katarzyna");
        vetKatarzyna.setLastName("Wlazlo");
        vetKatarzyna.getSpecialities().add(surgery);
        vets.add(vetKatarzyna);

        Vet vetMarek = new Vet();
        vetMarek.setFirstName("Marek");
        vetMarek.setLastName("Wlazlo");
        vetMarek.getSpecialities().add(surgery);
        vetMarek.getSpecialities().add(radiology);
        vets.add(vetMarek);

        Vet vetPatrycja = new Vet();
        vetPatrycja.setFirstName("Patrycja");
        vetPatrycja.setLastName("Wlazlo");
        vetPatrycja.getSpecialities().add(surgery);
        vetPatrycja.getSpecialities().add(radiology);
        vetPatrycja.getSpecialities().add(cardiology);
        vetPatrycja.getSpecialities().add(oncology);
        vets.add(vetPatrycja);

        return vets;
    }
}
