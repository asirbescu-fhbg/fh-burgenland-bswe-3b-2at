package io.muehlbachler.fhburgenland.swm.examination.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;
import io.muehlbachler.fhburgenland.swm.examination.repository.NoteRepository;
import io.muehlbachler.fhburgenland.swm.examination.service.NoteService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Implements the methods from {@link NoteService}.
 */
@NoArgsConstructor
@AllArgsConstructor
@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    /**
     * Retrieves a Note entity using its unique identifier.
     *
     * @see NoteService#get(String) 
     */
    @Override
    public Optional<Note> get(String id) {
        return noteRepository.findById(id);
    }

    /**
     * Creates a new Note entity using the provided Note object.
     *
     * @see NoteService#create(Note) 
     */
    @Override
    public Note create(Note note) {
        return noteRepository.save(note);
    }

    /**
     * Retrieves a list of Note entities whose content contains the query.
     *
     * @see NoteService#queryByContent(String) 
     */
    @Override
    public List<Note> queryByContent(String query) {
        return noteRepository.findByContentContaining(query);
    }
}
