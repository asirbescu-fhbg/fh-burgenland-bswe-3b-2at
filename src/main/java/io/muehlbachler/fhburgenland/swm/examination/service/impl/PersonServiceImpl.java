package io.muehlbachler.fhburgenland.swm.examination.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import io.muehlbachler.fhburgenland.swm.examination.model.Person;
import io.muehlbachler.fhburgenland.swm.examination.repository.PersonRepository;
import io.muehlbachler.fhburgenland.swm.examination.service.NoteService;
import io.muehlbachler.fhburgenland.swm.examination.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Implements the methods from {@link PersonService}.
 */
@NoArgsConstructor
@AllArgsConstructor
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private NoteService noteService;

    /**
     * Retrieves a list of all Person entities.
     *
     * @see PersonService#getAll()
     */
    public List<Person> getAll() {
        return Lists.newArrayList(personRepository.findAll());
    }

    /**
     * Retrieves a Person entity using its unique identifier.
     *
     * @see PersonService#get(String) 
     */
    public Optional<Person> get(String id) {
        return personRepository.findById(id);
    }

    /**
     * Creates a new Person using the Person object.
     *
     * @see PersonService#create(Person) 
     */
    @Override
    public Person create(Person person) {
        return personRepository.save(person);
    }

    /**
     * Retrieves a list of all Person entities whose firstname and lastname
     * match the given parameters.
     *
     * @see PersonService#findByName(String, String) 
     */
    @Override
    public List<Person> findByName(String firstName, String lastName) {
        if (!firstName.isEmpty() && lastName.isEmpty()) {
            return personRepository.findByFirstName(firstName);
        } else if (!lastName.isEmpty() && firstName.isEmpty()) {
            return personRepository.findByLastName(lastName);
        }
        return personRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    /**
     * Creates a Note for a Person with the specified ID.
     *
     * @see PersonService#createNote(String, Note) 
     */
    @Override
    public Optional<Note> createNote(String personId, Note note) {
        return get(personId).map((Person person) -> {
            note.setPerson(person);
            return noteService.create(note);
        });
    }
}
