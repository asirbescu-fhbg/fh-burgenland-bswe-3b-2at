package io.muehlbachler.fhburgenland.swm.examination.controller;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.muehlbachler.fhburgenland.swm.examination.model.Person;

import java.util.List;

import org.junit.jupiter.api.Assertions;

/**
 * Test class for {@code PersonController}.
 */
@SpringBootTest
public class PersonControllerTest {
    @Autowired
    private PersonController personController;

    @Test
    void testGetById() {
        ResponseEntity<Person> person =
                personController.get("81150016-8501-4b97-9168-01113e21d8a5");

        Assertions.assertEquals(HttpStatus.OK, person.getStatusCode(),
                "person should be found");
        Assertions.assertEquals("John",
                person.getBody().getFirstName(),
                "firstName should be John");
    }

    @Test
    void testCreatePerson() {
        Person expected = new Person();
        expected.setFirstName("Jonathan");
        expected.setLastName("John");

        Person actual = personController.create(expected);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(expected.getFirstName(), actual.getFirstName());
        Assertions.assertNull(actual.getNotes());
    }

    @Test
    void testGetByQuery() {
        List<Person> expected = personController.list().subList(1, 2);
        List<Person> actual = personController.query("Jane", "");
        Assertions.assertEquals(expected.size(), actual.size());
        Assertions.assertEquals(expected.getFirst().getFirstName(),
                actual.getFirst().getFirstName());
        Assertions.assertEquals(expected.getFirst().getLastName(),
                actual.getFirst().getLastName());
    }

    @Test
    void testCreateNoteIdNotFound() {
        Note note = new Note();
        note.setContent("Damn! This is a remarkable test");
        ResponseEntity<Note> actual =
                personController.createNote("1", note);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, actual.getStatusCode());
        Assertions.assertNull(actual.getBody());
    }

    @Test
    void testCreatePersonNullThrowsException() {
        Assertions.assertThrows(
                InvalidDataAccessApiUsageException.class,
                () -> personController.create(null));
    }


}