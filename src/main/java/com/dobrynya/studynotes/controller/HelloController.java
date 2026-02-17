package com.dobrynya.studynotes.controller;

import com.dobrynya.studynotes.dto.NoteCreateDTO;
import com.dobrynya.studynotes.dto.NoteResponseDTO;
import com.dobrynya.studynotes.model.Note;
import com.dobrynya.studynotes.model.NoteType;
import com.dobrynya.studynotes.service.NoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public NoteResponseDTO testCreate() {
        NoteCreateDTO dto = new NoteCreateDTO();
        dto.setTitle("Stream API — основы");
        dto.setContent("# Stream API\n\nStream — это поток данных...");
        dto.setType("LESSON");
        return noteService.save(dto);
    }

    @GetMapping("/api/test-all")
    public List<NoteResponseDTO> testAll() {
        return noteService.findAll();
    }

    @GetMapping("/api/test-delete/{id}")
    public String testDelete(@PathVariable Long id) {
        noteService.delete(id);
        return "Заметка с id=" + id + " удалена";
    }
}
