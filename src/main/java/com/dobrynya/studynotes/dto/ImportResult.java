package com.dobrynya.studynotes.dto;

import java.util.ArrayList;
import java.util.List;

public class ImportResult {
    private int imported;
    private int skipped;
    private final List<String> errors = new ArrayList<>();

    public void incrementImported() {
        this.imported++;
    }

    public void incrementSkipped() {
        this.skipped++;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public int getImported() {
        return imported;
    }

    public int getSkipped() {
        return skipped;
    }

    public List<String> getErrors() {
        return errors;
    }

    public int getTotal() {
        return imported + skipped + errors.size();
    }
}
