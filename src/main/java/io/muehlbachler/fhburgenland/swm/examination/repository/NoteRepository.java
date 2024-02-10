package io.muehlbachler.fhburgenland.swm.examination.repository;

import java.util.List;

import io.muehlbachler.fhburgenland.swm.examination.service.NoteService;
import org.springframework.data.repository.CrudRepository;

import io.muehlbachler.fhburgenland.swm.examination.model.Note;

public interface NoteRepository extends CrudRepository<Note, String> {

    /**
     * Retrieves a list of Note entities whose content contains the provided
     * content.
     * @param content The string for which the content is checked.
     * @return List of Notes whose content matches the provided content or an
     *         empty list if no Notes whose content matches the provided
     *         content are found or the provided content is null.
     *         Returns a list with all Notes if the provided content is empty.
     */
    List<Note> findByContentContaining(String content);
}
