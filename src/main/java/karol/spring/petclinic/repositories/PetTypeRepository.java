package karol.spring.petclinic.repositories;

import karol.spring.petclinic.models.PetType;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Karol Wlazło
 * pet-clinic
 */
public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
