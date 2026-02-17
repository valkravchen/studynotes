package com.dobrynya.studynotes.service;

import com.dobrynya.studynotes.dto.NoteCreateDTO;
import com.dobrynya.studynotes.dto.NoteResponseDTO;
import com.dobrynya.studynotes.exception.NoteNotFoundException;
import com.dobrynya.studynotes.mapper.NoteMapper;
import com.dobrynya.studynotes.model.Note;
import com.dobrynya.studynotes.model.NoteType;
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

    public NoteResponseDTO save(NoteCreateDTO dto) {
        Note note = noteMapper.toEntity(dto);
        Note saved = noteRepository.save(note);
        return noteMapper.toResponseDTO(saved);
    }

    public void delete(Long id) {
        if (!noteRepository.existsById(id)) {
            throw new NoteNotFoundException(id);
        }
        noteRepository.deleteById(id);
    }

    public NoteResponseDTO update(Long id, NoteCreateDTO dto) {
        Note existing = findNoteById(id);
        existing.setTitle(dto.getTitle());
        existing.setContent(dto.getContent());

        if (dto.getType() != null && !dto.getType().isBlank()) {
            existing.setType(NoteType.valueOf(dto.getType().toUpperCase()));
        }
        Note saved = noteRepository.save(existing);
        return noteMapper.toResponseDTO(saved);
    }

    private Note findNoteById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException(id));
    }
}
