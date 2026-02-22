package com.dobrynya.studynotes.service;

import com.dobrynya.studynotes.dto.ImportResult;
import com.dobrynya.studynotes.repository.NoteRepository;

public class ImportService {
    private final NoteRepository noteRepository;

    public ImportService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public ImportResult importFromFolder(String folderPath) {
        ImportResult result = new ImportResult();
        return result;
    }
}
