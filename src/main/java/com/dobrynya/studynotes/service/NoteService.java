package com.dobrynya.studynotes.service;

import com.dobrynya.studynotes.repository.NoteRepository;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
}
