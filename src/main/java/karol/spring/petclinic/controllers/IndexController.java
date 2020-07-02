package karol.spring.petclinic.controllers;

import karol.spring.petclinic.models.Owner;
import karol.spring.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


/**
 * @author Karol Wlazło
 * pet-clinic
 */

@Controller
public class IndexController {

    private final OwnerService ownerService;

    public IndexController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/")
    public String getIndexPage(){

        List<Owner> result = ownerService.findAll();
        for (Owner owner: result) {
            System.out.println(owner.getFirstName());
        }

        return "index.html";
    }
}
