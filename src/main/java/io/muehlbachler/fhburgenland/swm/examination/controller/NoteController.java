package io.muehlbachler.fhburgenland.swm.examination.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import io.muehlbachler.fhburgenland.swm.examination.service.NoteService;

/**
 * Handles incoming HTTP requests related to notes.
 */
@RestController
@RequestMapping("note")
public class NoteController {
    @Autowired
    private NoteService noteService;

    /**
     * Retrieves a Note entity using its unique identifier.
     *
     * @param id The unique string identifier of the Note entity to
     *           retrieve. Must not be null.
     * @return ResponseEntity containing the Note entity with HTTP status 200
     *         if found or an empty ResponseEntity with HTTP status 404 if
     *         no Note is found with the specified ID.
     * @throws RuntimeException if the provided ID is null.
     * @see Note
     * @see NoteService
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> get(@PathVariable String id) {
        return ResponseEntity.of(noteService.get(id));
    }

    /**
     * Retrieves a list of note entities whose content contains the query.
     *
     * @param query The string for which the content is checked.
     * @return List of notes whose content matches the query or an empty
     *         list if no notes whose content matches the query are found or
     *         the query is null. Returns a list with all notes if the query is
     *         empty.
     * @see Note
     * @see NoteService
     */
    @GetMapping("/query")
    public List<Note> query(@RequestParam("query") String query) {
        return noteService.queryByContent(query);
    }
}
