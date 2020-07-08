package karol.spring.petclinic.services;

import karol.spring.petclinic.models.Visit;
import karol.spring.petclinic.repositories.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Karol Wlazło
 * pet-clinic
 */
class VisitServiceImplTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitServiceImpl visitService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        visitService = new VisitServiceImpl(visitRepository);
    }

    @Test
    void findAll() {
        List<Visit> savedVisits = new ArrayList<>();

        Visit visit1 = new Visit();
        visit1.setId(1L);
        Visit visit2 = new Visit();
        visit2.setId(2L);

        savedVisits.add(visit1);
        savedVisits.add(visit2);

        when(visitRepository.findAll()).thenReturn(savedVisits);

        List<Visit> visits = visitService.findAll();

        assertNotNull(visits);
        assertEquals(2, visits.size());

        verify(visitRepository, times(1)).findAll();
    }

    @Test
    void save() {
        Visit savedVisit = new Visit();
        savedVisit.setId(1L);

        when(visitRepository.save(any())).thenReturn(savedVisit);

        Visit visit = visitService.save(savedVisit);

        assertNotNull(visit);
        assertEquals(savedVisit, visit);

        verify(visitRepository, times(1)).save(any());
    }
}