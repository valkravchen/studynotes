package com.dobrynya.studynotes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NoteCreateDTO {

    @NotBlank(message = "Заголовок не может быть пустым")
    @Size(max = 500, message = "Заголовок не может быть длиннее 500 символов")
    private String title;
    private String content;
    private String type;

    public NoteCreateDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
