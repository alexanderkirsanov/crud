package ru.susu.crud.editor;

import java.util.HashMap;
import java.util.Map;

public class TextEditor implements Editor {
    private static final int DEFAULT_SIZE = 10;
    private final int size;

    public TextEditor(int size) {
        this.size = size;
    }

    public TextEditor() {
        this.size = DEFAULT_SIZE;
    }

    @Override
    public Map<String, String[]> getDefinition() {
        Map<String, String[]> definition = new HashMap<String, String[]>();
        definition.put("type", new String[]{this.getClass().getSimpleName()});
        definition.put("size", new String[]{String.valueOf(size)});
        return definition;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextEditor that = (TextEditor) o;

        return size == that.size;

    }

    @Override
    public int hashCode() {
        return size;
    }
}
