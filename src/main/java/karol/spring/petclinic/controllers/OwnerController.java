package karol.spring.petclinic.controllers;

import karol.spring.petclinic.models.Owner;
import karol.spring.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


/**
 * @author Karol Wlazło
 * pet-clinic
 */

@RequestMapping("/owner")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping("/find")
    public String loadFindForm(Model model){

        model.addAttribute("owner", new Owner());

        return "findOwners";
    }

    @GetMapping
    public String processForm(Owner owner, BindingResult bindingResult, Model model){

        if(owner.getLastName() == null){
            System.out.println("ERROR");
            owner.setLastName("");
        }

        List<Owner> ownerList = ownerService.findByLastNameLikeIgnoreCase("%" + owner.getLastName() + "%");

        if(ownerList.isEmpty()){
            System.out.println("ERROR_1");
            return "findOwners";
        } else if(ownerList.size() == 1){
            owner = ownerList.get(0);
            return "redirect:/owner/" + owner.getId();
        } else{
            model.addAttribute("selections" , ownerList);
            return "ownersList";
        }
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwnerDetails(@PathVariable Long ownerId){

        ModelAndView modelAndView = new ModelAndView("OwnerDetails");
        modelAndView.addObject(ownerService.findById(ownerId));

        return modelAndView;
    }

    @GetMapping("/new")
    public String loadAddNewOwnerForm(Model model){

        model.addAttribute("owner", new Owner());

        return "addNewOwnerForm";
    }

    @PostMapping("/new")
    public String processAddNewOwnerForm(@ModelAttribute Owner owner, BindingResult result){

        if(result.hasErrors()){
            System.out.println("Nie udalo sie zapisac");
            return "redirect:/";
        }
        else{
            Owner savedOwner = ownerService.save(owner);
            System.out.println("Udalo sie zapisac");
            return "redirect:/owner/" + owner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String loadUpdateOwnerForm(@PathVariable Long ownerId, Model model){

        model.addAttribute("owner", ownerService.findById(ownerId));
        return "editOwner";
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateOwnerForm(@ModelAttribute Owner owner, BindingResult result, @PathVariable Long ownerId){

        if(result.hasErrors()){
            System.out.println("ERROR");
            return "editOwner";
        }else{
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);

            return "redirect:/owner/" + owner.getId();
        }
    }
}
