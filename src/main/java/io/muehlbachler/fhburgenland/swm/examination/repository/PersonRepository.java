package io.muehlbachler.fhburgenland.swm.examination.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import io.muehlbachler.fhburgenland.swm.examination.model.Person;

/**
 * Repository for managing CRUD operations on Person entities.
 */
public interface PersonRepository extends CrudRepository<Person, String> {

    /**
     * Retrieves a list of Person entities whose first name matches the given
     * first name (case-sensitive).
     *
     * @param firstName The string for which the first name is checked.
     * @return List of Person entities whose first name matches the provided
     *         first name or an empty list if no Person entities whose first
     *         name matches the provided first name are found or the provided
     *         first name is null. Returns a list with all Person entities if
     *         the provided first name is empty.
     */
    List<Person> findByFirstName(String firstName);

    /**
     * Retrieves a list of Person entities whose last name matches the given
     * last name (case-sensitive).
     *
     * @param lastName The string for which the last name is checked.
     * @return List of Person entities whose last name matches the provided
     *         last name or an empty list if no Person entities whose last
     *         name matches the provided last name are found or the provided
     *         last name is null. Returns a list with all Person entities if
     *         the provided last name is empty.
     */
    List<Person> findByLastName(String lastName);

    /**
     * Retrieves a list of Person entities whose first and last name matches
     * the given parameters (case-sensitive).
     *
     * @param firstName The string for which the first name is checked.
     * @param lastName The string for which the last name is checked.
     * @return List of Person entities whose first and last name matches the
     *         provided first and last name or an empty list if no Person entities
     *         whose first and last name matches the provided first and last
     *         name are found or the provided first and last name are null.
     *         Returns a list with all Person entities if the provided first
     *         and last name is empty.
     */
    List<Person> findByFirstNameAndLastName(String firstName, String lastName);
}
