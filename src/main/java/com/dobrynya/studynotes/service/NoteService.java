package com.dobrynya.studynotes.service;

import com.dobrynya.studynotes.dto.NoteResponseDTO;
import com.dobrynya.studynotes.exception.NoteNotFoundException;
import com.dobrynya.studynotes.mapper.NoteMapper;
import com.dobrynya.studynotes.model.Note;
import com.dobrynya.studynotes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    public NoteService(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    public List<NoteResponseDTO> findAll() {
        List<Note> notes = noteRepository.findAll();
        return noteMapper.toResponseDTOList(notes);
    }
    
    public NoteResponseDTO findById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));
        return noteMapper.toResponseDTO(note);
    }

    public Note save(Note note) {
        return noteRepository.save(note);
    }

    public void delete(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new NoteNotFoundException(id);
        }
        noteRepository.deleteById(id);
    }

    public Note update(Long id, Note updateNote) {
        Note existing = findById(id);
        existing.setTitle(updateNote.getTitle());
        existing.setContent(updateNote.getContent());
        existing.setType(updateNote.getType());
        return noteRepository.save(existing);
    }
}
