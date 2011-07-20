package ru.susu.crud.xml;

public class Column {
    private String name;
    private String type;
    private boolean isNull;
    private int size;
    private String editor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isNull() {
        return isNull;
    }

    public void setNull(boolean aNull) {
        isNull = aNull;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Column{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", isNull=" + isNull +
                ", size=" + size +
                ", editor=" + editor +
                '}';
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }
}
