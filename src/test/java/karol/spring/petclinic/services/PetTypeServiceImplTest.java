package karol.spring.petclinic.services;

import karol.spring.petclinic.models.PetType;
import karol.spring.petclinic.repositories.PetTypeRepository;
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
class PetTypeServiceImplTest {

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeServiceImpl petTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        petTypeService = new PetTypeServiceImpl(petTypeRepository);
    }

    @Test
    void findAll() {
        List<PetType> returnedPetTypes = new ArrayList<>();

        PetType petType1 = new PetType();
        petType1.setId(1L);
        PetType petType2= new PetType();
        petType2.setId(2L);
        returnedPetTypes.add(petType1);
        returnedPetTypes.add(petType2);

        when(petTypeRepository.findAll()).thenReturn(returnedPetTypes);

        List<PetType> types = petTypeService.findAll();

        assertNotNull(types);
        assertEquals(2, types.size());

        verify(petTypeRepository, times(1)).findAll();
    }

    @Test
    void save() {
        PetType savedPetType = new PetType();
        savedPetType.setId(1L);

        when(petTypeRepository.save(any())).thenReturn(savedPetType);

        PetType petType = petTypeService.save(savedPetType);

        assertNotNull(petType);
        assertEquals(petType, savedPetType);
        verify(petTypeRepository, times(1)).save(any());
    }

    @Test
    void findByName() {
        PetType savedPetType = new PetType();
        savedPetType.setId(1L);
        savedPetType.setName("Test");

        when(petTypeRepository.findAll()).thenReturn(Arrays.asList(savedPetType));

        PetType petType = petTypeService.findByName(savedPetType.getName());

        assertNotNull(petType);
        assertEquals(petType, savedPetType);

        verify(petTypeRepository, times(1)).findAll();
    }
}