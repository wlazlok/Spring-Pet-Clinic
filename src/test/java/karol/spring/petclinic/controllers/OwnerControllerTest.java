package karol.spring.petclinic.controllers;

import karol.spring.petclinic.models.Owner;
import karol.spring.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    void showOwnerDetails() {
    }

    @Test
    void loadAddNewOwnerForm() {
    }

    @Test
    void processAddNewOwnerForm() {
    }

    @Test
    void loadUpdateOwnerForm() {
    }

    @Test
    void processUpdateOwnerForm() {
    }
}