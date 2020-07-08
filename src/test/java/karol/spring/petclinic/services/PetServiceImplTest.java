package karol.spring.petclinic.services;

import karol.spring.petclinic.models.Owner;
import karol.spring.petclinic.models.Pet;
import karol.spring.petclinic.repositories.PetRepository;
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
class PetServiceImplTest {

    @Mock
    PetRepository petRepository;

    @InjectMocks
    PetServiceImpl petService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        petService = new PetServiceImpl(petRepository);
    }

    @Test
    void findAll() {
        List<Pet> returnedPets = new ArrayList<>();

        Pet pet1 = new Pet();
        pet1.setId(1L);
        Pet pet2 = new Pet();
        pet2.setId(2L);
        returnedPets.add(pet1);
        returnedPets.add(pet2);

        when(petRepository.findAll()).thenReturn(returnedPets);

        List<Pet> pets = petService.findAll();

        assertNotNull(pets);
        assertEquals(2, pets.size());
        verify(petRepository, times(1)).findAll();
    }

    @Test
    void save() {
        Pet petToSave = new Pet();
        petToSave.setId(1L);

        when(petRepository.save(any())).thenReturn(petToSave);

        Pet pet = petService.save(petToSave);

        assertNotNull(pet);
        assertEquals(pet, petToSave);
        verify(petRepository).save(any());
    }

    @Test
    void deleteById() {
        Pet pet = new Pet();
        pet.setId(1L);

        petRepository.deleteById(1L);

        verify(petRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void findById() {
        Pet pet = new Pet();
        pet.setId(1L);

        when(petRepository.findById(any())).thenReturn(Optional.of(pet));

        Pet pet1 = petService.findById(pet.getId());

        assertNotNull(pet1);
        assertEquals(pet, pet1);

        verify(petRepository, times(1)).findById(anyLong());
    }
}