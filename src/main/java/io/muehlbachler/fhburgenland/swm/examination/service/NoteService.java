package io.muehlbachler.fhburgenland.swm.examination.service;

import java.util.List;
import java.util.Optional;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import io.muehlbachler.fhburgenland.swm.examination.model.Person;

public interface NoteService {

    /**
     * Retrieves a Note entity using its unique identifier.
     * @param id The unique string identifier of the Note entity to
     *           retrieve. Must not be null.
     * @return Optional containing the Note entity if found or
     *         an empty Optional if no Note is found with the specified ID.
     * @see Note
     * @see Optional
     */
    Optional<Note> get(String id);

    /**
     * Creates a new Note entity using the provided Note object.
     * @param note The Note object to be created. Must not be null or
     *             incomplete.
     * @return The created Note entity.
     * @see Note
     */
    Note create(Note note);

    /**
     * Retrieves a list of Note entities whose content contains the query.
     * @param query The string for which the content is checked.
     * @return List of Note entities whose content matches the query or
     *         an empty list if no Note entities whose content matches the
     *         query are found or the query is null.
     *         Returns a list with all Note entities if the query is empty.
     * @see Note
     */
    List<Note> queryByContent(String query);
}
