package karol.spring.petclinic.repositories;

import karol.spring.petclinic.models.Speciality;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Karol Wlazło
 * pet-clinic
 */
public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
