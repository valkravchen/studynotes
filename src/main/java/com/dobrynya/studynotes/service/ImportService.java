package com.dobrynya.studynotes.service;

import com.dobrynya.studynotes.dto.ImportResult;
import com.dobrynya.studynotes.model.Note;
import com.dobrynya.studynotes.model.NoteType;
import com.dobrynya.studynotes.repository.NoteRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
        Path folder = Path.of(folderPath);

        if (!Files.exists(folder) || !Files.isDirectory(folder)) {
            result.addError("Папка не найдена: " + folderPath);
            return result;
        }

        List<Path> mdFiles = getMdFiles(folder);

        if (mdFiles.isEmpty()) {
            result.addError("В папке нет .md файлов");
            return result;
        }

        for (Path file : mdFiles) {
            processFile(file, result);
        }

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
        try (Stream<Path> stream = Files.list(folder)) {
            return stream
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".md"))
                    .toList();
        } catch (IOException e) {
            return List.of();
        }
    }

    private void processFile(Path file, ImportResult result) {
        try {
            String content = Files.readString(file, StandardCharsets.UTF_8);
            String fileName = file.getFileName().toString();
            String title = parseTitle(content, fileName);

            if (noteRepository.existsByTitle(title)) {
                result.incrementSkipped();
                return;
            }

            Note note = new Note();
            note.setTitle(title);
            note.setContent(content);
            note.setType(NoteType.LESSON);

            noteRepository.save(note);
            result.incrementImported();

        } catch (IOException e) {
            result.addError("Ошибка чтения файла: " + file.getFileName());
        }
    }
}
