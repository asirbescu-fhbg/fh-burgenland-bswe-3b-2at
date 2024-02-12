package io.muehlbachler.fhburgenland.swm.examination.controller;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

/**
 * Test class for {@code NoteController}.
 */
@SpringBootTest
class NoteControllerTest {

	@Autowired
	private NoteController noteController;

	@Test
	void testGetById() {
		ResponseEntity<Note> note =
				noteController.get("c5b38625-7eed-4705-858d-c685f18ed47d");

		Assertions.assertEquals(HttpStatus.OK, note.getStatusCode());
		Assertions.assertEquals("John",
				Objects.requireNonNull(note.getBody()).getPerson().getFirstName());
	}

	@Test
	void testGetByIdNotFound() {
		ResponseEntity<Note> note =
				noteController.get("gits ned - doch, bei roller!");
		Assertions.assertEquals(HttpStatus.NOT_FOUND, note.getStatusCode());
		Assertions.assertNull(note.getBody());
	}

	@Test
	void testGetByQuery() {
		List<Note> notes = noteController.query("Note");
		Assertions.assertNotNull(notes);
		Assertions.assertEquals(1, notes.size());
		Assertions.assertEquals("Note 1", notes.getFirst().getContent());
	}

	@Test
	void testGetByEmptyQuery() {
		List<Note> notes = noteController.query("");
		Assertions.assertNotNull(notes);
		Assertions.assertEquals(1, notes.size());
		Assertions.assertEquals("Note 1", notes.getFirst().getContent());
	}
}