package com.dobrynya.studynotes.controller;

import com.dobrynya.studynotes.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

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
}
