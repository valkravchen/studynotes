package com.dobrynya.studynotes.service;

import com.dobrynya.studynotes.exception.NoteNotFoundException;
import com.dobrynya.studynotes.model.Note;
import com.dobrynya.studynotes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public Note findById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }
}
