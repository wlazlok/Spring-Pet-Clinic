package karol.spring.petclinic.services;

import karol.spring.petclinic.models.Vet;
import karol.spring.petclinic.repositories.VetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karol Wlazło
 * pet-clinic
 */

@Service
public class VerServiceImpl implements VerService {

    private final VetRepository vetRepository;

    public VerServiceImpl(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public List<Vet> findAll() {
        List<Vet> vets = new ArrayList<>();
        vetRepository.findAll().forEach(vets::add);

        return vets;
    }

    @Override
    public Vet save(Vet obj) {
        return null;
    }
}
