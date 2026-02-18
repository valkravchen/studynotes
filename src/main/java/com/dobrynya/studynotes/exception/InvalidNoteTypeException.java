package com.dobrynya.studynotes.exception;

public class InvalidNoteTypeException extends RuntimeException {

    public InvalidNoteTypeException(String type) {
        super("Невалидный тип заметки: " + type + ". Допустимые значения: LESSON, REVIEW, CHEATSHEET, GUIDE");
    }
}
