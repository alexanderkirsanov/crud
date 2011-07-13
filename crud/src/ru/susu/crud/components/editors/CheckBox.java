package ru.susu.crud.components.editors;

public class CheckBox extends CustomEditor {
    public CheckBox(String name) {
        super(name);
    }

    public boolean checked() {
        return (this.getValue() != null);
    }
}
