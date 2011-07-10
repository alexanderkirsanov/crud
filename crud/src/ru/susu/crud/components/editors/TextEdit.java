package ru.susu.crud.components.editors;

//TODO getHTMLvalue from TextEdit, customAttributes for TextEdit
public class TextEdit extends CustomEditor {
    private int size;
    private int maxLength;
    private boolean passwordMode;
    private boolean allowHtmlCharacters;

    public TextEdit(String name) {
        super(name);
        this.allowHtmlCharacters = true;
    }

    public TextEdit(String name, int size, int maxLength) {
        this(name);
        this.size = size;
        this.maxLength = maxLength;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public boolean getPasswordMode() {
        return passwordMode;
    }

    public void setPasswordMode(boolean passwordMode) {
        this.passwordMode = passwordMode;
    }

    public boolean getAllowHtmlCharacters() {
        return allowHtmlCharacters;
    }

    public void setAllowHtmlCharacters(boolean allowHtmlCharacters) {
        this.allowHtmlCharacters = allowHtmlCharacters;
    }

}
