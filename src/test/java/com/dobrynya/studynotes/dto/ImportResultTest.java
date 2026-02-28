package com.dobrynya.studynotes.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
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
}