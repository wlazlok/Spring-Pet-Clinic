package karol.spring.petclinic.repositories;

import karol.spring.petclinic.models.Vet;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Karol Wlazło
 * pet-clinic
 */
public interface VetRepository extends CrudRepository<Vet, Long> {
}
