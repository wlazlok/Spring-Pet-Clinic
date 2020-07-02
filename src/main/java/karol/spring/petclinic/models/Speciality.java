package karol.spring.petclinic.models;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Karol Wlazło
 * pet-clinic
 */

@Entity
public class Speciality extends BaseEntity{

    @Column(name = "name")
    private String name;

    public Speciality() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
