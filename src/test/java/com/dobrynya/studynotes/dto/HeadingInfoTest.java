package com.dobrynya.studynotes.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HeadingInfoTest {
    @Test
    @DisplayName("Конструктор корректно сохраняет H2 заголовок")
    void constructorSetsH2LevelAndText() {
        HeadingInfo heading = new HeadingInfo(2, "Создание стримов");
        assertEquals(2, heading.getLevel());
        assertEquals("Создание стримов", heading.getText());
    }

    @Test
    @DisplayName("Конструктор корректно сохраняет H3 заголовок")
    void constructorSetsH3LevelAndText() {
        HeadingInfo heading = new HeadingInfo(3, "Stream.of()");
        assertEquals(3, heading.getLevel());
        assertEquals("Stream.of()", heading.getText());
    }

    @Test
    @DisplayName("HeadingInfo принимает пустой текст заголовка")
    void acceptsEmptyText() {
        HeadingInfo heading = new HeadingInfo(2, "");
        assertEquals(2, heading.getLevel());
        assertEquals("", heading.getText());
    }
}