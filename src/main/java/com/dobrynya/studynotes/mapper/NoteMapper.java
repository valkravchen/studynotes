package com.dobrynya.studynotes.mapper;

import com.dobrynya.studynotes.dto.NoteCreateDTO;
import com.dobrynya.studynotes.dto.NoteResponseDTO;
import com.dobrynya.studynotes.model.Note;
import com.dobrynya.studynotes.model.NoteType;
import org.springframework.stereotype.Component;

@Component
public class NoteMapper {
    public Note toEntity(NoteCreateDTO dto) {
        Note note = new Note();
        note.setTitle(dto.getTitle());
        note.setContent(dto.getContent());

        if (dto.getType() != null && !dto.getType().isBlank()) {
            note.setType(NoteType.valueOf(dto.getType().toUpperCase()));
        }
        return note;
    }

    public NoteResponseDTO toResponseDTO(Note note) {
        NoteResponseDTO dto = new NoteResponseDTO();
        dto.setId(note.getId());
        dto.setTitle(note.getTitle());
        dto.setContent(note.getContent());
        dto.setCreatedAt(note.getCreatedAt());
        dto.setUpdatedAt(note.getUpdatedAt());

        if (note.getType() != null) {
            dto.setType(note.getType().name().toUpperCase());
        }
        return dto;
    }
}
