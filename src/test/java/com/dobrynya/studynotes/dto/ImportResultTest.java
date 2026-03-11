package com.dobrynya.studynotes.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImportResultTest {
    private ImportResult result;

    @BeforeEach
    void setUp() {
        result = new ImportResult();
    }

    @Test
    @DisplayName("Новый ImportResult имеет нулевые значения")
    void newImportResultHasZeroValues() {
        assertEquals(0, result.getImported(), "imported должен быть 0");
        assertEquals(0, result.getSkipped(), "skipped должен быть 0");
        assertTrue(result.getErrors().isEmpty(), "errors должен быть пустым");
        assertEquals(0, result.getTotal(), "total должен быть 0");
    }

    @Test
    @DisplayName("incrementImported() увеличивает счётчик")
    void incrementImportedIncreasesCounter() {
        result.incrementImported();
        result.incrementImported();
        assertEquals(2, result.getImported());
    }

    @Test
    @DisplayName("incrementSkipped() увеличивает счётчик")
    void incrementSkippedIncreasesCounter() {
        result.incrementSkipped();
        result.incrementSkipped();
        result.incrementSkipped();
        assertEquals(3, result.getSkipped());
    }

    @Test
    @DisplayName("addError() добавляет ошибки в список")
    void addErrorAppendsToErrorList() {
        result.addError("Ошибка чтения файла: notes.md");
        result.addError("Файл повреждён: broken.md");

        assertEquals(2, result.getErrors().size());
        assertEquals("Ошибка чтения файла: notes.md", result.getErrors().get(0));
        assertEquals("Файл повреждён: broken.md", result.getErrors().get(1));
    }

    @Test
    @DisplayName("getTotal() возвращает сумму imported + skipped + errors")
    void getTotalReturnsSumOfAllCategories() {
        result.incrementImported();
        result.incrementImported();
        result.incrementImported();
        result.incrementSkipped();
        result.incrementSkipped();
        result.addError("Ошибка");

        int total = result.getTotal();
        assertEquals(6, total, "3 imported + 2 skipped + 1 error = 6");
    }


    @Test
    @DisplayName("Счётчики imported и skipped независимы друг от друга")
    void countersAreIndependent() {
        result.incrementImported();
        result.incrementImported();
        result.incrementSkipped();
        assertEquals(2, result.getImported(),"imported не должен зависеть от skipped");
        assertEquals(1, result.getSkipped(), "skipped не должен зависеть от imported");
        assertTrue(result.getErrors().isEmpty(),"errors не должен меняться от инкрементов");
    }
}