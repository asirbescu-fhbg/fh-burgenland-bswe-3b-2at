package io.muehlbachler.fhburgenland.swm.examination.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import io.muehlbachler.fhburgenland.swm.examination.model.Person;
import io.muehlbachler.fhburgenland.swm.examination.service.PersonService;

/**
 * Handles incoming HTTP requests related to persons.
 */
@RestController
@RequestMapping("person")
public class PersonController {
    @Autowired
    private PersonService personService;

    /**
     * Retrieves a list of all Person entities.
     *
     * @return A list of all stored Person entities or an empty list if no
     *         entities are stored.
     * @see Person
     * @see PersonService
     */
    @GetMapping("/")
    public List<Person> list() {
        return personService.getAll();
    }

    /**
     * Retrieves a Person entity using its unique identifier.
     *
     * @param id The unique string identifier of the Person entity to
     *           retrieve. Must not be null.
     * @return ResponseEntity containing the Person entity with HTTP status 200
     *         if found or an empty ResponseEntity with HTTP status 404 if
     *         no Note is found with the specified ID.
     * @throws IllegalArgumentException if the provided ID is null.
     * @see Person
     * @see PersonService
     */
    @GetMapping("/{id}")
    public ResponseEntity<Person> get(@PathVariable String id) {
        return ResponseEntity.of(personService.get(id));
    }

    /**
     * Creates a new Person using the Person object.
     *
     * @param person The Person object to be created, which contains the
     *               information that is not generated automatically. Must
     *               not be null or incomplete.
     * @return The created Person object.
     * @throws IllegalArgumentException if the provided Person is null.
     * @see Person
     * @see PersonService
     */
    @PostMapping("/")
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

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
     * @see PersonService
     */
    @GetMapping("/query")
    public List<Person> query(@RequestParam("firstName") String firstName,
                              @RequestParam("lastName") String lastName) {
        return personService.findByName(firstName, lastName);
    }

    /**
     * Creates a Note for a Person with the specified ID.
     *
     * @param id The unique string identifier of the Person entity for which
     *           the Note is to be created. Must not be null.
     * @param note The Note object to be created, which contains the
     *             information that is not generated automatically. Must
     *             not be null or incomplete.
     * @return ResponseEntity containing the Note entity with HTTP status 200
     *         if Person is found or an empty ResponseEntity with HTTP status
     *         404 if no Person is found with the specified ID.
     * @see Person
     * @see PersonService
     */
    @PostMapping("/{id}/note")
    public ResponseEntity<Note> createNote(@PathVariable String id, @RequestBody Note note) {
        return ResponseEntity.of(personService.createNote(id, note));
    }
}
