package ru.susu.crud.components.editors;

/**
 * Created by IntelliJ IDEA.
 * User: dem
 * Date: 09.07.11
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
public class TextEdit {
    private String name;
    private int size;
    private int maxLength;
    private boolean passwordMode;
    private Object value;
    private boolean allowHtmlCharacters;

    public TextEdit(String name) {
        this.name = name;
        this.allowHtmlCharacters = true;
    }

    public TextEdit(String name, int size, int maxLength) {
        this(name);
        this.size = size;
        this.maxLength = maxLength;
    }

    public String getName() {
        return this.name;
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

    public Object getValue() {
        return value;
    }

    public boolean getAllowHtmlCharacters() {
        return allowHtmlCharacters;
    }

    public void setAllowHtmlCharacters(boolean allowHtmlCharacters) {
        this.allowHtmlCharacters = allowHtmlCharacters;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
