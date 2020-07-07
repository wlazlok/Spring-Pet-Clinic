package karol.spring.petclinic.controllers;

import karol.spring.petclinic.models.Vet;
import karol.spring.petclinic.services.VerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Karol Wlazło
 * pet-clinic
 */
class VetsControllerTest {

    @Mock
    VerService verService;

    @InjectMocks
    VetsController vetsController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        vetsController = new VetsController(verService);
        mockMvc = MockMvcBuilders.standaloneSetup(vetsController).build();
    }

    @Test
    void getVets() throws Exception {
        Vet vet1 = new Vet();
        vet1.setId(1L);

        Vet vet2 = new Vet();
        vet2.setId(2L);

        when(verService.findAll()).thenReturn(Arrays.asList(vet1, vet2));

        mockMvc.perform(get("/vets"))
                .andExpect(status().isOk())
                .andExpect(view().name("veterinarias.html"))
                .andExpect(model().attributeExists("vets"))
                .andExpect(model().attribute("vets", hasSize(2)));
    }
}