package ru.susu.crud.components.editors;

public abstract class CustomEditor {
    private String name;

    public CustomEditor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
