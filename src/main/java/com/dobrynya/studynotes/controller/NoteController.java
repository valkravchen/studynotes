package com.dobrynya.studynotes.controller;

import com.dobrynya.studynotes.dto.NoteCreateDTO;
import com.dobrynya.studynotes.dto.NoteResponseDTO;
import com.dobrynya.studynotes.service.NoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public List<NoteResponseDTO> findAll(@RequestParam(required = false) String type) {
        return noteService.findAll(type);
    }

    @GetMapping("/{id}")
    public NoteResponseDTO findById(@PathVariable Long id) {
        return noteService.findById(id);
    }

    @PostMapping
    public ResponseEntity<NoteResponseDTO> create(@Valid @RequestBody NoteCreateDTO dto) {
        NoteResponseDTO saved = noteService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public NoteResponseDTO update(@PathVariable Long id, @Valid @RequestBody NoteCreateDTO dto) {
        return noteService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        noteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
