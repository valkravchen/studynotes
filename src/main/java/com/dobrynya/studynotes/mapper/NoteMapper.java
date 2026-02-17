package com.dobrynya.studynotes.mapper;

import com.dobrynya.studynotes.dto.NoteCreateDTO;
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
}
