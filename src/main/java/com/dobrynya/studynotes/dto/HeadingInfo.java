package com.dobrynya.studynotes.dto;

public class HeadingInfo {
    private final int level;
    private final String text;


    public HeadingInfo(int level, String text) {
        this.level = level;
        this.text = text;
    }

    public int getLevel() {
        return level;
    }

    public String getText() {
        return text;
    }
}
