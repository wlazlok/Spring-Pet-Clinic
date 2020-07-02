package karol.spring.petclinic.services;

import karol.spring.petclinic.models.Speciality;
import karol.spring.petclinic.repositories.SpecialityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karol Wlazło
 * pet-clinic
 */

@Service
public class SpecialityServiceImpl implements SpecialityService {

    private final SpecialityRepository specialityRepository;

    public SpecialityServiceImpl(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public List<Speciality> findAll() {
        List<Speciality> specialities = new ArrayList<>();
        specialityRepository.findAll().forEach(specialities::add);

        return specialities;
    }
}
