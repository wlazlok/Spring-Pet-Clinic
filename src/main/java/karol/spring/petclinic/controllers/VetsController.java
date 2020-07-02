package karol.spring.petclinic.controllers;

import karol.spring.petclinic.services.VerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Karol Wlazło
 * pet-clinic
 */

@Controller
public class VetsController {

    private final VerService vetService;

    public VetsController(VerService vetService) {
        this.vetService = vetService;
    }

    @GetMapping("/vets")
    public String getVets(Model model){

        model.addAttribute("vets", vetService.findAll());

        return "veterinarias.html";
    }
}
