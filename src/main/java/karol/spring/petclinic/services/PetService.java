package karol.spring.petclinic.services;

import karol.spring.petclinic.models.Pet;

import java.util.Optional;

/**
 * @author Karol Wlazło
 * pet-clinic
 */
public interface PetService extends BaseService<Pet, Long>{

    void deleteById(Long id);

    Pet findById(Long id);
}
