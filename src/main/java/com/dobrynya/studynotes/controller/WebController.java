package com.dobrynya.studynotes.controller;

import com.dobrynya.studynotes.dto.NoteCreateDTO;
import com.dobrynya.studynotes.dto.NoteResponseDTO;
import com.dobrynya.studynotes.model.NoteType;
import com.dobrynya.studynotes.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String listNotes(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String type,
            Model model
    ) {
        List<NoteResponseDTO> notes = noteService.findAll(type, search);
        model.addAttribute("notes", notes);
        model.addAttribute("search", search);
        model.addAttribute("selectedType", type);
        model.addAttribute("types", NoteType.values());
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

    @PostMapping("/notes")
    public String createNote(
            @RequestParam String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String type
    ) {
        NoteCreateDTO dto = new NoteCreateDTO();
        dto.setTitle(title);
        dto.setContent(content);
        dto.setType(type);

        noteService.save(dto);

        return "redirect:/notes";
    }

    @GetMapping("/notes/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        NoteResponseDTO note = noteService.findById(id);
        model.addAttribute("note", note);
        return "note-edit";
    }

    @PostMapping("/notes/{id}/edit")
    public String updateNote(
            @PathVariable Long id,
            @RequestParam String title,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String type
    ) {
        NoteCreateDTO dto = new NoteCreateDTO();
        dto.setTitle(title);
        dto.setContent(content);
        dto.setType(type);
        noteService.update(id, dto);
        return "redirect:/notes";
    }
}
