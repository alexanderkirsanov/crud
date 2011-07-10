package ru.susu.crud.components.editors;

public class TextAreaEdit extends CustomEditor {
    private int columnCount;
    private int rowCount;
    private boolean allowHtmlCharacters;

    public TextAreaEdit(String name){
        super(name);
        this.allowHtmlCharacters = true;
    }

    public TextAreaEdit(String name, int columnCount, int rowCount) {
        this(name);
        this.columnCount = columnCount;
        this.rowCount = rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public boolean getAllowHtmlCharacters() {
        return allowHtmlCharacters;
    }

    public void setAllowHtmlCharacters(boolean allowHtmlCharacters) {
        this.allowHtmlCharacters = allowHtmlCharacters;
    }
}
