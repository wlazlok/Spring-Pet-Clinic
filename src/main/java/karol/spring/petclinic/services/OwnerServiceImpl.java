package karol.spring.petclinic.services;

import karol.spring.petclinic.models.Owner;
import karol.spring.petclinic.repositories.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Karol Wlazło
 * pet-clinic
 */

@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }


    @Override
    public List<Owner> findAll() {
        List<Owner> owners = new ArrayList<>();
        ownerRepository.findAll().forEach(owners::add);

        return owners;
    }

    @Override
    public Owner save(Owner obj) {
        return ownerRepository.save(obj);
    }
}
