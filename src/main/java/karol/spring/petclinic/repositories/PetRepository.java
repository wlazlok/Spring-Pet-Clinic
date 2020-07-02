package karol.spring.petclinic.repositories;

import karol.spring.petclinic.models.Pet;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Karol Wlazło
 * pet-clinic
 */

public interface PetRepository extends CrudRepository<Pet, Long> {
}
