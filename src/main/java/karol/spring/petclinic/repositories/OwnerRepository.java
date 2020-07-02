package karol.spring.petclinic.repositories;

import karol.spring.petclinic.models.Owner;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Karol Wlazło
 * pet-clinic
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {

}
