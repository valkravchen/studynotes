package com.dobrynya.studynotes.exception;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(Long id) {
        super("Заметка с id " + id + " не найдена");
    }
}
