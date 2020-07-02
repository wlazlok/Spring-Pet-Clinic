package karol.spring.petclinic.repositories;

import karol.spring.petclinic.models.Visit;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Karol Wlazło
 * pet-clinic
 */
public interface VisitRepository extends CrudRepository<Visit, Long> {
}
