package karol.spring.petclinic.controllers;

import karol.spring.petclinic.models.Owner;
import karol.spring.petclinic.models.Pet;
import karol.spring.petclinic.models.PetType;
import karol.spring.petclinic.services.OwnerService;
import karol.spring.petclinic.services.PetService;
import karol.spring.petclinic.services.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.beans.Transient;
import java.time.LocalDate;
import java.util.*;

import static org.springframework.util.StringUtils.hasLength;
import static org.springframework.util.StringUtils.sortStringArray;

/**
 * @author Karol Wlazło
 * pet-clinic
 */
@Controller
@RequestMapping("/owner/{ownerId}")
public class PetController {

    private final OwnerService ownerService;
    private final PetService petService;
    private final PetTypeService petTypeService;

    public PetController(OwnerService ownerService, PetService petService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable("ownerId") Long ownerId){
        return ownerService.findById(ownerId);
    }

    @ModelAttribute("types")
    public List<PetType> populatePetTypes(){
        return this.petTypeService.findAll();
    }

    @InitBinder
    public void dataBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");

        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException{
                setValue(LocalDate.parse(text));
            }
        });
    }

    @GetMapping("/pets/new")
    public String initCreationForm(Owner owner, Model model){

        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);

        return "createNewPetForm";
    }

    @PostMapping("/pets/new")
    public String processCreationForm(Owner owner, @Validated Pet pet, BindingResult result, ModelMap model) {

        if (hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        owner.getPets().add(pet);
        pet.setOwner(owner);
        if (result.hasErrors()) {
            model.put("pet", pet);
            return "createNewPetForm";
        } else {
            pet.setPetType(petTypeService.findByName(pet.getPetType().getName()));
            petService.save(pet);
            return "redirect:/owner/" + owner.getId();
        }
    }


    @GetMapping("/pet/{petId}/delete")
    public String deletePetById(Owner owner, @PathVariable Long petId){

        petService.deleteById(petId);

        return "redirect:/owner/" + owner.getId();
    }

    @GetMapping("/pet/{petId}/edit")
    public String initUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return "createNewPetForm";
    }

    @PostMapping("/pet/{petId}/edit")
    @Transactional
    public String processUpdatePet(@Validated Pet pet, BindingResult result, Owner owner, Model model, @PathVariable Long petId){

        if(result.hasErrors()){
            model.addAttribute("pet", pet);
            return "createNewPetForm";
        }
        else {
            Pet savedPet = petService.findById(petId);

            savedPet.setOwner(owner);
            owner.getPets().add(savedPet);
            savedPet.setBirthDate(pet.getBirthDate());
            savedPet.setPetType(petTypeService.findByName(pet.getPetType().getName()));

            petService.save(savedPet);

            return "redirect:/owner/" + owner.getId();
        }
    }
}


