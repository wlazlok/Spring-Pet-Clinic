package karol.spring.petclinic.services;

import karol.spring.petclinic.models.Speciality;
import karol.spring.petclinic.repositories.SpecialityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Karol Wlazło
 * pet-clinic
 */
class SpecialityServiceImplTest {

    @Mock
    SpecialityRepository specialityRepository;

    @InjectMocks
    SpecialityServiceImpl specialityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        specialityService = new SpecialityServiceImpl(specialityRepository);
    }

    @Test
    void findAll() {
        List<Speciality> savedSpecialities = new ArrayList<>();

        Speciality speciality1 = new Speciality();
        speciality1.setId(1L);
        Speciality speciality2 = new Speciality();
        speciality2.setId(2L);
        savedSpecialities.add(speciality1);
        savedSpecialities.add(speciality2);

        when(specialityRepository.findAll()).thenReturn(savedSpecialities);

        List<Speciality> specialities = specialityService.findAll();

        assertNotNull(specialities);
        assertEquals(2, specialities.size());

        verify(specialityRepository, times(1)).findAll();
    }

    @Test
    void save() {
        // function is not implemented
        /*Speciality savedSpeciality = new Speciality();
        savedSpeciality.setId(1L);

        when(specialityRepository.save(any())).thenReturn(savedSpeciality);

        Speciality speciality = specialityService.save(savedSpeciality);

        assertNotNull(savedSpeciality);
        assertEquals(speciality, savedSpeciality);

        verify(specialityRepository, times(1)).save(any());*/
    }
}