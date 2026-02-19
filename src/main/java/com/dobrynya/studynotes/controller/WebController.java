package com.dobrynya.studynotes.controller;

import com.dobrynya.studynotes.dto.NoteResponseDTO;
import com.dobrynya.studynotes.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class WebController {
    private final NoteService noteService;

    public WebController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("appName", "StudyNotes");
        model.addAttribute("noteCount", noteService.findAll(null).size());
        model.addAttribute("currentDate", LocalDateTime.now());
        return "index";
    }

    @GetMapping("/notes")
    public String listNotes(Model model) {
        List<NoteResponseDTO> notes = noteService.findAll(null);
        model.addAttribute("notes", notes);
        return "note-list";
    }

    @PostMapping("/notes/{id}/delete")
    public String deleteNote(@PathVariable Long id) {
        noteService.delete(id);
        return "redirect:/notes";
    }

    @GetMapping("/notes/new")
    public String showCreateForm() {
        return "note-form";
    }
}
