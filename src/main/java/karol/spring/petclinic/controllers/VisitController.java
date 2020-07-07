package karol.spring.petclinic.controllers;

import karol.spring.petclinic.models.Owner;
import karol.spring.petclinic.models.Pet;
import karol.spring.petclinic.models.Visit;
import karol.spring.petclinic.services.OwnerService;
import karol.spring.petclinic.services.PetService;
import karol.spring.petclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Karol Wlazło
 * pet-clinic
 */
@Controller
@RequestMapping("/owner/{ownerId}/pet/{petId}/visit")
public class VisitController {

    private final VisitService visitService;
    private final OwnerService ownerService;
    private final PetService petService;

    public VisitController(VisitService visitService, OwnerService ownerService, PetService petService) {
        this.visitService = visitService;
        this.ownerService = ownerService;
        this.petService = petService;
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId){
        return ownerService.findById(ownerId);
    }

    @ModelAttribute("pet")
    public Pet findPet(@PathVariable Long petId){

        return petService.findById(petId);
    }

    @GetMapping("/new")
    public String initCreateNewVisitForm(@PathVariable Long petId, Model model){

        model.addAttribute("pet", petService.findById(petId));
        return "createNewVisitForm";
    }

    @ModelAttribute("visitsList")
    public List<Visit> getVisitList(@PathVariable Long petId){
        Pet pet = petService.findById(petId);
        return pet.getVisits();
    }

    @ModelAttribute("visit")
    public Visit loadPetVisit(@PathVariable Long petId, Map<String, Object> model){
        Pet pet = petService.findById(petId);
        System.out.println(pet.getName());
        Visit visit = new Visit();
        pet.getVisits().add(visit);
        visit.setPet(pet);

        return visit;
    }

    @PostMapping("/new")
    public String processAddVisit(@Validated Visit visit, BindingResult result){
        if(result.hasErrors()){
            return "createNewVisitForm";
        }else{
            System.out.println(visit.getDescription());
            System.out.println(visit.getPet().getName());
            visitService.save(visit);
            return "redirect:/owner/{ownerId}/pet/{petId}/visit/new";
        }
    }
}
