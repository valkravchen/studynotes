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

    @Test
    @DisplayName("Заголовки # и ## рендерятся в <h1> и <h2>")
    void renderHeadings() {
        String markdown = "# Заголовок первого уровня\n\n## Заголовок второго уровня";
        String html = markdownService.renderToHtml(markdown);
        assertTrue(html.contains("<h1>"), "Должен содержать <h1>");
        assertTrue(html.contains("Заголовок первого уровня"), "Должен содержать текст H1");
        assertTrue(html.contains("<h2>"), "Должен содержать <h2>");
        assertTrue(html.contains("Заголовок второго уровня"), "Должен содержать текст H2");
    }
}