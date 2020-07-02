package karol.spring.petclinic.boostrapData;

import karol.spring.petclinic.models.Owner;
import karol.spring.petclinic.repositories.OwnerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * @author Karol Wlazło
 * pet-clinic
 */

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerRepository ownerRepository;

    public DataLoader(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ownerRepository.saveAll(loadOwners());
    }

    public List<Owner> loadOwners(){

        List<Owner> owners = new ArrayList<>();

        Owner ownerKarol = new Owner();
        ownerKarol.setFirstName("Karol");
        ownerKarol.setLastName("Wlazło");
        ownerKarol.setCity("Balice");
        owners.add(ownerKarol);

        return owners;
    }
}
