package karol.spring.petclinic.services;

import karol.spring.petclinic.models.Owner;
import karol.spring.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author Karol Wlazło
 * pet-clinic
 */
class OwnerServiceImplTest {

    @Mock
    OwnerRepository ownerRepository;

    @InjectMocks
    OwnerServiceImpl ownerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        ownerService = new OwnerServiceImpl(ownerRepository);
    }

    @Test
    void findAll() {
        List<Owner> returnedOwners = new ArrayList<>();
        Owner owner1 = new Owner();
        owner1.setId(1L);
        Owner owner2 = new Owner();
        owner2.setId(2L);
        returnedOwners.add(owner1);
        returnedOwners.add(owner2);

        when(ownerRepository.findAll()).thenReturn(returnedOwners);

        List<Owner> owners = ownerService.findAll();

        assertNotNull(owners);
        assertEquals(2, owners.size());
    }

    @Test
    void save() {
        Owner ownerToSave = new Owner();
        ownerToSave.setId(1L);

        when(ownerRepository.save(any())).thenReturn(ownerToSave);

        Owner savedOwner = ownerService.save(ownerToSave);

        assertNotNull(savedOwner);
        assertEquals(ownerToSave, savedOwner);
        verify(ownerRepository).save(any());
    }

    @Test
    void findByLastNameLikeIgnoreCase() {
        Owner returnOwner = new Owner();
        returnOwner.setId(1L);
        returnOwner.setLastName("Test");

        when(ownerRepository.findByLastNameLikeIgnoreCase(anyString())).thenReturn(Arrays.asList(returnOwner));

        List<Owner> owner = ownerService.findByLastNameLikeIgnoreCase(returnOwner.getLastName());

        assertNotNull(owner);
        assertEquals(1, owner.size());
        assertEquals(returnOwner, owner.get(0));

        verify(ownerRepository, times(1)).findByLastNameLikeIgnoreCase(anyString());
    }

    @Test
    void findById() {
        Owner returnOwner = new Owner();
        returnOwner.setId(1L);

        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));

        Owner owner = ownerService.findById(returnOwner.getId());

        assertNotNull(owner);
        assertEquals(owner, returnOwner);
        verify(ownerRepository, times(1)).findById(anyLong());
    }
}