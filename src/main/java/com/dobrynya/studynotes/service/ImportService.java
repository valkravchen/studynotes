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

    private String parseTitle(String content, String fileName) {
        String fallback = fileName.endsWith(".md")
                ? fileName.substring(0, fileName.length() - 3)
                : fileName;

        return content.lines()
                .filter(line -> line.startsWith("# "))
                .findFirst()
                .map(line -> line.substring(2).trim())
                .orElse(fallback);
    }
}
