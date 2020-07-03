package karol.spring.petclinic.services;

import java.util.*;

/**
 * @author Karol Wlazło
 * pet-clinic
 */
public interface BaseService<T, ID> {

    List<T> findAll();

    T save(T obj);
}
