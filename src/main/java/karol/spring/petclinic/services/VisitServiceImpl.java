package karol.spring.petclinic.services;

import karol.spring.petclinic.models.Visit;
import karol.spring.petclinic.repositories.VisitRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karol Wlazło
 * pet-clinic
 */

@Service
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;

    public VisitServiceImpl(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public List<Visit> findAll() {
        List<Visit> visits = new ArrayList<>();
        visitRepository.findAll().forEach(visits::add);

        return visits;
    }

    @Override
    public Visit save(Visit obj) {
        return null;
    }
}
