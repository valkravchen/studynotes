package com.dobrynya.studynotes.service;

import com.dobrynya.studynotes.dto.HeadingInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

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

    @Test
    @DisplayName("**жирный** → <strong>, *курсив* → <em>")
    void renderBoldAndItalic() {
        String markdown = "Это **жирный** и *курсивный* текст";
        String html = markdownService.renderToHtml(markdown);
        assertTrue(html.contains("<strong>жирный</strong>"), "Должен рендерить **жирный**");
        assertTrue(html.contains("<em>курсивный</em>"), "Должен рендерить *курсив*");
    }

    @Test
    @DisplayName("Блок кода рендерится в <pre><code>")
    void renderCodeBlock() {
        String markdown = "```java\nString hello = \"world\";\n```";
        String html = markdownService.renderToHtml(markdown);
        assertTrue(html.contains("<pre>"), "Должен содержать <pre>");
        assertTrue(html.contains("<code"), "Должен содержать <code>");
        assertTrue(html.contains("String hello"), "Должен содержать код");
    }

    @Test
    @DisplayName("Ссылка [текст](url) рендерится в <a href>")
    void renderLink() {
        String markdown = "[Spring Docs](https://spring.io)";
        String html = markdownService.renderToHtml(markdown);
        assertTrue(html.contains("<a"), "Должен содержать тег <a>");
        assertTrue(html.contains("href=\"https://spring.io\""), "Должен содержать URL");
        assertTrue(html.contains("Spring Docs"), "Должен содержать текст ссылки");
    }

    @Test
    @DisplayName("GFM таблица рендерится в <table>")
    void renderGfmTable() {
        String markdown = "| Заголовок | Значение |\n| --- | --- |\n| Ячейка 1 | Ячейка 2 |";
        String html = markdownService.renderToHtml(markdown);
        assertTrue(html.contains("<table>"), "Должен содержать <table>");
        assertTrue(html.contains("<th>"), "Должен содержать заголовки таблицы <th>");
        assertTrue(html.contains("<td>"), "Должен содержать ячейки <td>");
        assertTrue(html.contains("Ячейка 1"), "Должен содержать данные ячеек");
    }

    @Test
    @DisplayName("Инлайн-код `text` рендерится в <code>")
    void renderInlineCode() {
        String markdown = "Используй `NoteService` для работы с заметками";
        String html = markdownService.renderToHtml(markdown);
        assertTrue(html.contains("<code>NoteService</code"), "Должен обернуть в <code>");
    }

    @ParameterizedTest(name = "extractHeadings({0}) → пустой список")
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    void extractHeadingsFromBlankInputReturnsEmptyList(String input) {
        List<HeadingInfo> headings = markdownService.extractHeadings(input);
        assertTrue(headings.isEmpty(), "Для пустого ввода список заголовков должен быть пустым");
    }

    @Test
    @DisplayName("H1 заголовок не попадает в оглавление")
    void h1IsExcludedFromHeadings() {
        String markdown = "# Это заголовок первого уровня";
        List<HeadingInfo> headings = markdownService.extractHeadings(markdown);
        assertTrue(headings.isEmpty(), "H1 не должен попадать в оглавление");
    }
}