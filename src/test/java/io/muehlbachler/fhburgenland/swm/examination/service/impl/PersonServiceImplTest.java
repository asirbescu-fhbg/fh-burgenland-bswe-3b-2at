package io.muehlbachler.fhburgenland.swm.examination.service.impl;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import io.muehlbachler.fhburgenland.swm.examination.model.Person;
import io.muehlbachler.fhburgenland.swm.examination.repository.PersonRepository;
import io.muehlbachler.fhburgenland.swm.examination.service.NoteService;
import io.muehlbachler.fhburgenland.swm.examination.service.PersonService;

/**
 * Test class for {@code PersonServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
public class PersonServiceImplTest {
    @Mock
    private NoteService noteService;
    @Mock
    private PersonRepository personRepository;

    private PersonService personService;

    @BeforeEach
    void setUp() {
        personService = new PersonServiceImpl(personRepository, noteService);
    }

    @AfterEach
    void tearDown() {
        Mockito.verifyNoMoreInteractions(personRepository);
        Mockito.verifyNoMoreInteractions(noteService);
    }

    @Test
    void testGetAll() {
        List<Person> allMockedPersons = new ArrayList<>();
        allMockedPersons.add(new Person("1", "Daniel", "Mühlbachler", null));
        allMockedPersons.add(new Person("2", "Amalia", "Sirbescu",
                Lists.newArrayList()));
        Mockito.when(personRepository.findAll()).thenReturn(allMockedPersons);

        List<Person> allPersons = personService.getAll();

        Assertions.assertNotNull(allPersons);
        Assertions.assertEquals(allPersons.size(), allMockedPersons.size());
        Assertions.assertNotNull(allPersons.getLast().getNotes());
        Assertions.assertNull(allPersons.getFirst().getNotes());

        Mockito.verify(personRepository, times(1)).findAll();
    }

    @Test
    void testGetById() {
        Mockito.when(personRepository.findById("1"))
                .thenReturn(Optional.of(new Person("1", "John", "Doe", Lists.newArrayList())));

        Optional<Person> person = personService.get("1");

        Assertions.assertTrue(person.isPresent());
        Assertions.assertEquals("John", person.get().getFirstName(),
                "firstName should be John");

        Mockito.verify(personRepository, times(1)).findById("1");
    }

    @Test
    void testCreatePerson() {
        Person person = new Person("2", "Created", "Person", Lists.newArrayList());

        Mockito.when(personRepository.save(Mockito.any(Person.class)))
                .thenReturn(new Person("2", "Created", "Person",
                        Lists.newArrayList()));

        Person createdPerson = personService.create(person);

        Assertions.assertNotNull(createdPerson);
        Assertions.assertEquals("Created", createdPerson.getFirstName(),
                "firstName should be Created");
        Assertions.assertNotNull(createdPerson.getNotes());
        Mockito.verify(personRepository, times(1)).save(person);
    }

    @Test
    void testFindByName() {
        List<Person> expected = Lists.newArrayList();
        expected.add(new Person("1", "John", "Doe", Lists.newArrayList()));
        expected.add(new Person("3", "John", "Kramer", null));
        Mockito.when(personRepository.findByFirstName("John"))
                .thenReturn(expected);
        List<Person> actual = personService.findByName("John", "");
        Assertions.assertEquals(expected, actual);
    }

    @Test
   void testCreateNote() {
        String personId = "1";
        Person person = new Person(personId, null, null, null);
        Note note = new Note();

        Mockito.when(personService.get(personId)).thenReturn(Optional
                .of(person));
        Mockito.when(noteService.create(note)).thenReturn(note);

        Optional<Note> createdNote = personService.createNote(personId, note);

        Assertions.assertTrue(createdNote.isPresent());
        Assertions.assertEquals(note, createdNote.get());
        Assertions.assertEquals(person, createdNote.get().getPerson());
        Mockito.verify(noteService, times(1)).create(note);
    }
}
