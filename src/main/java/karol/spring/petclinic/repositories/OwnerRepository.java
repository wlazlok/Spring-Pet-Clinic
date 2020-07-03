package karol.spring.petclinic.repositories;

import karol.spring.petclinic.models.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Karol Wlazło
 * pet-clinic
 */
public interface OwnerRepository extends CrudRepository<Owner, Long> {
    List<Owner> findByLastNameLikeIgnoreCase(String lastName);
}
