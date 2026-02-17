package com.dobrynya.studynotes.controller;

import com.dobrynya.studynotes.dto.NoteResponseDTO;
import com.dobrynya.studynotes.service.NoteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<NoteResponseDTO> findAll() {
        return noteService.findAll();
    }

    @GetMapping("/{id}")
    public NoteResponseDTO findById(@PathVariable Long id) {
        return noteService.findById(id);
    }
}
