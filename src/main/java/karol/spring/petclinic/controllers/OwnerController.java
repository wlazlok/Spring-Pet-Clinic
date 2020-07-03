package karol.spring.petclinic.controllers;

import karol.spring.petclinic.models.Owner;
import karol.spring.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Karol Wlazło
 * pet-clinic
 */

@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owner/new")
    public String loadAddNewOwnerForm(Model model){

        model.addAttribute("owner", new Owner());

        return "addNewOwnerForm";
    }

    @PostMapping("/owner/new")
    public String processAddNewOwnerForm(@ModelAttribute Owner owner, BindingResult result){

        if(result.hasErrors()){
            System.out.println("Nie udalo sie zapisac");
            return "redirect:/";
        }
        else{
            Owner savedOwner = ownerService.save(owner);
            System.out.println("Udalo sie zapisac");
            return "redirect:/";
        }
    }
}
