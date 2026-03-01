package com.dobrynya.studynotes.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}