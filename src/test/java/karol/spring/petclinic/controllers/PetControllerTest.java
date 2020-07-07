package karol.spring.petclinic.controllers;

import karol.spring.petclinic.models.Owner;
import karol.spring.petclinic.models.Pet;
import karol.spring.petclinic.models.PetType;
import karol.spring.petclinic.services.OwnerService;
import karol.spring.petclinic.services.PetService;
import karol.spring.petclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Karol Wlazło
 * pet-clinic
 */
class PetControllerTest {

    @Mock
    OwnerService ownerService;

    @Mock
    PetService petService;

    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;

    List<PetType> petTypes;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        petController = new PetController(ownerService, petService, petTypeService);

        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();

        petTypes = new ArrayList<>();

        PetType petType1 = new PetType();
        petType1.setId(1L);
        PetType petType2 = new PetType();
        petType2.setId(2L);

        petTypes.add(petType1);
        petTypes.add(petType2);
    }

    @Test
    void initCreationForm() throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(get("/owner/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("createNewPetForm"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void processCreationForm() throws Exception {
        /*Owner owner = new Owner();
        owner.setId(1L);
        PetType petType = new PetType();
        petType.setId(3L);
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petTypeService.findByName(anyString())).thenReturn(petType);

        mockMvc.perform(post("/owner/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owner/1"));*/
    }

    @Test
    void deletePetById() throws Exception {
        Pet pet = new Pet();
        pet.setId(1L);
        Owner owner = new Owner();
        owner.setId(1L);

        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(get("/owner/1/pet/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owner/1"));

        verify(petService, times(1)).deleteById(anyLong());
        verify(ownerService, times(1)).findById(anyLong());
    }

    @Test
    void initUpdateForm() throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);
        Pet pet = new Pet();
        pet.setId(1L);
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
        when(petService.findById(anyLong())).thenReturn(pet);

        mockMvc.perform(get("/owner/1/pet/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("createNewPetForm"))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"));

        verify(petService, times(1)).findById(anyLong());
        verify(ownerService, times(1)).findById(anyLong());
        verify(petTypeService, times(1)).findAll();
    }

    @Test
    void processUpdatePet() throws Exception {
       /* Owner owner = new Owner();
        owner.setId(1L);
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);

        mockMvc.perform(post("/owner/1/pet/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owner/1"));*/
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
    void populatePetTypes() {
        when(petTypeService.findAll()).thenReturn(petTypes);

        assertNotNull(petTypes);
        assertEquals(petTypes, petTypeService.findAll());
        assertEquals(2, petTypes.size());
    }

    @Test
    void dataBinder() {
        //todo
    }
}