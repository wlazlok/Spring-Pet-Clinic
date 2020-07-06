package karol.spring.petclinic.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

/**
 * @author Karol Wlazło
 * pet-clinic
 */

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "owners")
public class Owner extends Person{

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    List<Pet> pets = new ArrayList<>();


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public Pet getPet(String name, boolean ignoreNew){
        name = name.toLowerCase();
        for(Pet pet: pets){
            if(!ignoreNew || !pet.isNew()){
                String compName = pet.getName();
                compName = compName.toLowerCase();
                if(compName.equals(name)){
                    return pet;
                }
            }
        }
        return null;
    }
}
