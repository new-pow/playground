package com.practice.notion.domain;

public class Text extends Content {

    private String value;

    public Text(Long id, String value) {
        super(id);
        this.value = value;
    }

    public Text(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + System.lineSeparator();
    }
}
