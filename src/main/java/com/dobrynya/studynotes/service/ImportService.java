package com.dobrynya.studynotes.service;

import com.dobrynya.studynotes.dto.ImportResult;
import com.dobrynya.studynotes.repository.NoteRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

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

    private List<Path> getMdFiles(Path folder) {
        try(Stream<Path> stream = Files.list(folder)) {
           return stream
                   .filter(Files::isRegularFile)
                   .filter(path -> path.toString().endsWith(".md"))
                   .toList();
        } catch (IOException e) {
            return List.of();
        }
    }
}
