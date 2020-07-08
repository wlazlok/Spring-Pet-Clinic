package karol.spring.petclinic.services;

import karol.spring.petclinic.models.Vet;
import karol.spring.petclinic.repositories.VetRepository;
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
class VerServiceImplTest {

    @Mock
    VetRepository vetRepository;

    @InjectMocks
    VerServiceImpl vetService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        vetService = new VerServiceImpl(vetRepository);
    }

    @Test
    void findAll() {
        List<Vet> savedVets = new ArrayList<>();

        Vet vet1 = new Vet();
        vet1.setId(1L);
        Vet vet2 = new Vet();
        vet2.setId(2L);
        savedVets.add(vet1);
        savedVets.add(vet2);

        when(vetRepository.findAll()).thenReturn(savedVets);

        List<Vet> vets = vetService.findAll();

        assertNotNull(vets);
        assertEquals(2, vets.size());

        verify(vetRepository, times(1)).findAll();
    }
}