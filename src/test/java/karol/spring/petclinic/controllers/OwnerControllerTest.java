package karol.spring.petclinic.controllers;

import karol.spring.petclinic.models.Owner;
import karol.spring.petclinic.services.OwnerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Karol Wlazło
 * pet-clinic
 */
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ownerController = new OwnerController(ownerService);

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void loadFindForm() throws Exception {
        mockMvc.perform(get("/owner/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("findOwners"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void processFormWithManyOwners() throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        Owner owner2 = new Owner();
        owner1.setId(2L);
        when(ownerService.findByLastNameLikeIgnoreCase(anyString()))
                .thenReturn(Arrays.asList(owner1, owner2));

        mockMvc.perform(get("/owner"))
                .andExpect(status().isOk())
                .andExpect(view().name("ownersList"))
                .andExpect(model().attribute("selections", hasSize(2)));
    }

    @Test
    void processFormWithOneOwner() throws Exception {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        when(ownerService.findByLastNameLikeIgnoreCase(anyString()))
                .thenReturn(Arrays.asList(owner1));

        mockMvc.perform(get("/owner"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owner/1"));
    }

    @Test
    void processFormWithEmptyList() throws Exception {
        when(ownerService.findByLastNameLikeIgnoreCase(anyString()))
                .thenReturn(Arrays.asList());

        mockMvc.perform(get("/owner"))
                .andExpect(status().isOk())
                .andExpect(view().name("findOwners"));
    }

    @Test
    void showOwnerDetails() throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);
        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(get("/owner/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("OwnerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
    }

    @Test
    void loadAddNewOwnerForm() throws Exception {
        mockMvc.perform(get("/owner/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("addNewOwnerForm"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void processAddNewOwnerForm() throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);
        when(ownerService.save((ArgumentMatchers.any()))).thenReturn(owner);

        mockMvc.perform(post("/owner/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owner/null"))
                .andExpect(model().attributeExists("owner"));

        Assertions.assertEquals(1, owner.getId());
        assertNotNull(owner);
    }


    @Test
    void loadUpdateOwnerForm() throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);

        when(ownerService.findById(anyLong())).thenReturn(owner);

        mockMvc.perform(get("/owner/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("editOwner"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void processUpdateOwnerForm() throws Exception {
        Owner owner = new Owner();
        owner.setId(1L);
        when(ownerService.save(ArgumentMatchers.any())).thenReturn(owner);

        mockMvc.perform(post("/owner/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owner/1"))
                .andExpect(model().attributeExists("owner"));
    }
}