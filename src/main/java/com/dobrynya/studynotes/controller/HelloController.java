package com.dobrynya.studynotes.controller;

import com.dobrynya.studynotes.model.Note;
import com.dobrynya.studynotes.model.NoteType;
import com.dobrynya.studynotes.service.NoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    private final NoteService noteService;

    public HelloController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/api/hello")
    public String hello() {
        return "Привет, StudyNotes!";
    }

    @GetMapping("/api/info")
    public String info() {
        return "StudyNotes — приложение для управления конспектами. Версия: 1.0";
    }

    @GetMapping("api/search")
    public String search(@RequestParam String query) {
        return "Ищем заметки по запросу: " + query;
    }

    @GetMapping("/api/test-create")
    public String testCreate() {
        Note note = new Note();
        note.setTitle("Stream API — основы");
        note.setContent("# Stream API\n\nStream — это поток данных...");
        note.setType(NoteType.LESSON);
        Note saved = noteService.save(note);
        return "Создана заметка с id=" + saved.getId() + ", title=" + saved.getTitle();
    }

    @GetMapping("/api/test-all")
    public List<Note> testAll() {
        return noteService.findAll();
    }
}
