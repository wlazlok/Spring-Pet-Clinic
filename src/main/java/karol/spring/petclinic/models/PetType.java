package karol.spring.petclinic.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Karol Wlazło
 * pet-clinic
 */

@Entity
@Table(name = "pet_types")
public class PetType extends BaseEntity{

    @Column(name = "name")
    private String name;

    public PetType() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
