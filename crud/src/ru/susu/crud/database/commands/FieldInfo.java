package ru.susu.crud.database.commands;

public class FieldInfo {
    private String tableName;
    private String name;
    private int fieldType;
    private String alias;

    public FieldInfo(String tableName, String name, int fieldType, String alias) {
        this.tableName = tableName;
        this.name = name;
        this.fieldType = fieldType;
        this.alias = alias;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFieldType() {
        return fieldType;
    }

    public void setFieldType(int fieldType) {
        this.fieldType = fieldType;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public String toString() {
        return "FieldInfo{" +
                "tableName='" + tableName + '\'' +
                ", name='" + name + '\'' +
                ", fieldType=" + fieldType +
                ", alias='" + alias + '\'' +
                '}';
    }
}
