package karol.spring.petclinic.controllers;

import karol.spring.petclinic.models.Owner;
import karol.spring.petclinic.models.Pet;
import karol.spring.petclinic.services.OwnerService;
import karol.spring.petclinic.services.PetService;
import karol.spring.petclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Karol Wlazło
 * pet-clinic
 */
class VisitControllerTest {

    @Mock
    VisitService visitService;

    @Mock
    OwnerService ownerService;

    @Mock
    PetService petService;

    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        visitController = new VisitController(visitService, ownerService, petService);
        mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void findOwner() {
        Owner owner = new Owner();
        owner.setId(1L);
        when(ownerService.findById(anyLong())).thenReturn(owner);

        assertEquals(owner, ownerService.findById(anyLong()));
        assertNotNull(owner);
    }

    @Test
    void findPet() {
        Pet pet = new Pet();
        pet.setId(1L);
        when(petService.findById(anyLong())).thenReturn(pet);

        assertEquals(pet, petService.findById(anyLong()));
        assertNotNull(pet);
    }

    @Test
    void initCreateNewVisitForm() throws Exception {
        Pet pet = new Pet();
        pet.setId(1L);
        Owner owner = new Owner();
        owner.setId(1L);
        when(petService.findById(anyLong())).thenReturn(pet);
        when(ownerService.findById(anyLong())).thenReturn(owner);
        mockMvc.perform(get("/owner/1/pet/1/visit/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("createNewVisitForm"))
                .andExpect(model().attributeExists("pet"));

        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    void getVisitList() {
    }

    @Test
    void loadPetVisit() {
    }

    @Test
    void processAddVisit() {
    }
}