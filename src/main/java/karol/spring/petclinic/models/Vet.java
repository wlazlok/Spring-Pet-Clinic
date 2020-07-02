package karol.spring.petclinic.models;

import javax.persistence.*;
import java.util.*;

/**
 * @author Karol Wlazło
 * pet-clinic
 */

@Entity
public class Vet extends Person{

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "vet_speciality",
            joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "speciality_id")
    )
    private List<Speciality> specialities = new ArrayList<>();

    public Vet() {
    }

    public List<Speciality> getSpecialities() {
        return specialities;
    }

    public void setSpecialities(List<Speciality> specialities) {
        this.specialities = specialities;
    }
}
