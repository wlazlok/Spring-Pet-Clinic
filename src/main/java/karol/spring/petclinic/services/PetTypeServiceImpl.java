package karol.spring.petclinic.services;

import karol.spring.petclinic.models.PetType;
import karol.spring.petclinic.repositories.PetTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Karol Wlazło
 * pet-clinic
 */

@Service
public class PetTypeServiceImpl implements PetTypeService {

    private final PetTypeRepository petTypeRepository;

    @Autowired
    public PetTypeServiceImpl(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public List<PetType> findAll() {

        List<PetType> petTypes = new ArrayList<>();
        petTypeRepository.findAll().forEach(petTypes::add);

        return petTypes;
    }

    @Override
    public PetType save(PetType obj) {
        return petTypeRepository.save(obj);
    }

    @Override
    public PetType findByName(String name) {
        Iterable<PetType> result = petTypeRepository.findAll();
        PetType searchedPetType = null;
        for (PetType type: result) {
            if(type.getName().equals(name))
                searchedPetType = type;
        }
        return searchedPetType;
    }
}
