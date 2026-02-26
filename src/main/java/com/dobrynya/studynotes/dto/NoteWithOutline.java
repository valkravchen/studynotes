package com.dobrynya.studynotes.dto;

import java.util.List;

public class NoteWithOutline {
    private final NoteResponseDTO note;
    private final List<HeadingInfo> headings;

    public NoteWithOutline(NoteResponseDTO note, List<HeadingInfo> headings) {
        this.note = note;
        this.headings = headings;
    }

    public NoteResponseDTO getNote() {
        return note;
    }

    public List<HeadingInfo> getHeadings() {
        return headings;
    }
}
