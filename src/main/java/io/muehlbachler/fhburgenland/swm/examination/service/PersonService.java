package io.muehlbachler.fhburgenland.swm.examination.service;

import java.util.List;
import java.util.Optional;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import io.muehlbachler.fhburgenland.swm.examination.model.Person;

/**
 * Service interface for managing CRUD operations on Person entities.
 */
public interface PersonService {

    /**
     * Retrieves a list of all Person entities.
     *
     * @return A list of all stored Person entities or an empty list if no
     *         entities are stored.
     * @see Person
     */
    List<Person> getAll();

    /**
     * Retrieves a Person entity using its unique identifier.
     *
     * @param id The unique string identifier of the Person entity to
     *           retrieve. Must not be null.
     * @return Optional containing the Person entity if found or an empty
     *         Optional if no Person is found with the specified ID.
     * @see Person
     * @see Optional
     */
    Optional<Person> get(String id);

    /**
     * Creates a new Person using the Person object.
     *
     * @param person The Person object to be created, which contains the
     *               information that is not generated automatically. Must
     *               not be null or incomplete.
     * @return The created Person object.
     * @see Person
     */
    Person create(Person person);

    /**
     * Retrieves a list of all Person entities whose firstname and lastname
     * match the given parameters.
     *
     * @param firstName The string representing the first name to search for.
     *                  Must not be null. If it is empty, only the last name
     *                  will be searched for.
     * @param lastName The string representing the last name to search for.
     *                 Must not be null. If it is empty, only the first name
     *                 will be searched for.
     * @return A list of all Person entities whose firstname and/or lastname
     *         match the given parameters or an empty list if no match found.
     * @see Person
     */
    // if any name (first or last) is empty, we only search for the other one
    List<Person> findByName(String firstName, String lastName);

    /**
     * Creates a Note for a Person with the specified ID.
     *
     * @param personId The unique string identifier of the Person entity for which
     *                 the Note is to be created. Must not be null.
     * @param note The Note object to be created, which contains the
     *             information that is not generated automatically. Must
     *             not be null or incomplete.
     * @return Optional containing the Note entity if Person is found or
     *         an empty Optional if no Person is found with the specified ID.
     * @see Note
     * @see Optional
     */
    Optional<Note> createNote(String personId, Note note);
}
