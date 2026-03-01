package com.dobrynya.studynotes.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MarkdownServiceTest {
    private MarkdownService markdownService;

    @BeforeEach
    void setUp() {
        markdownService = new MarkdownService();
    }

    @Test
    @DisplayName("null на входе → пустая строка")
    void renderNullReturnsEmptyString() {
        String result = markdownService.renderToHtml(null);
        assertEquals("", result);
    }

    @Test
    @DisplayName("Пустая строка на входе → пустая строка")
    void renderEmptyStringReturnsEmptyString() {
        String result = markdownService.renderToHtml("");
        assertEquals("", result);
    }

    @Test
    @DisplayName("Строка из пробелов → пустая строка")
    void renderBlankStringReturnsEmptyString() {
        String result = markdownService.renderToHtml("   ");
        assertEquals("", result);
    }

    @Test
    @DisplayName("Обычный текст рендерится как параграф <p>")
    void renderPlainTextAsParagraph() {
        String markdown = "Просто текст без разметки";
        String html = markdownService.renderToHtml(markdown);
        assertTrue(html.contains("<p>"), "Должен содержать тег <p>");
        assertTrue(html.contains("Просто текст без разметки"), "Должен содержать исходный текст");
    }
}