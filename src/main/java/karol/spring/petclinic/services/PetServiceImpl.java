package karol.spring.petclinic.services;

import karol.spring.petclinic.models.Pet;
import karol.spring.petclinic.repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karol Wlazło
 * pet-clinic
 */

@Service
public class PetServiceImpl implements PetService {

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public List<Pet> findAll() {
        List<Pet> pets = new ArrayList<>();
        petRepository.findAll().forEach(pets::add);
        return pets;
    }

    @Override
    public Pet save(Pet obj) {
        return null;
    }
}
