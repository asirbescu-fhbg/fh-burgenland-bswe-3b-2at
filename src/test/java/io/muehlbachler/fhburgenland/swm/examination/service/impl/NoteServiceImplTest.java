package io.muehlbachler.fhburgenland.swm.examination.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import io.muehlbachler.fhburgenland.swm.examination.model.Person;
import io.muehlbachler.fhburgenland.swm.examination.repository.NoteRepository;
import io.muehlbachler.fhburgenland.swm.examination.service.NoteService;

@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {

	@Mock
	private NoteRepository noteRepository;
	private NoteService noteService;

	@BeforeEach
	void setUp() {
		noteService = new NoteServiceImpl(noteRepository);
	}

	@AfterEach
	void tearDown() {
		Mockito.verifyNoMoreInteractions(noteRepository);
	}

	@Test
	void testGetById() {
		String id = "1";
		String content = "Content to be found";

		Mockito.when(noteRepository.findById(id))
				.thenReturn(Optional.of(new Note(id,
						Mockito.any(Person.class), content)));

		Optional<Note> note = noteService.get(id);

		Assertions.assertTrue(note.isPresent());
		Assertions.assertEquals(content, note.get().getContent());
		Mockito.verify(noteRepository, Mockito.times(1)).findById(id);
	}

	@Test
	void testCreateNote() {
		String id = "1";
		String content = "Content to be found";
		Person person = new Person();
		Note note = new Note(id, person, content);

		Mockito.when(noteRepository.save(Mockito.any(Note.class)))
				.thenReturn(new Note(id, person, content));

		Note createdNote = noteService.create(note);

		Assertions.assertNotNull(createdNote);
		Assertions.assertEquals(content, createdNote.getContent());
		Assertions.assertNotNull(createdNote.getPerson());
		Mockito.verify(noteRepository, Mockito.times(1)).save(note);

	}

	@Test
	void testGetNotesByContent() {
		String query = "Content";
		List<Note> expectedNotes = new ArrayList<>();
		expectedNotes.add(new Note("1", new Person(), "Content to be found"));
		expectedNotes.add(new Note("2", new Person(), "This Content should "
				+ "also be found."));

		Mockito.when(noteRepository.findByContentContaining(query))
				.thenReturn(expectedNotes);

		List<Note> actualFoundNotes = noteService.queryByContent(query);

		Assertions.assertEquals(expectedNotes, actualFoundNotes);
	}
}