package ru.susu.crud.database.commands;

public class JoinInfo {
    private int joinKind;
    private String table;
    private FieldInfo field;
    private String linkField; //?
    private String alias;

    public JoinInfo(int joinKind, String table, FieldInfo field, String linkField, String alias) {
        this.joinKind = joinKind;
        this.table = table;
        this.field = field;
        this.linkField = linkField;
        this.alias = alias;
    }

    public int getJoinKind() {
        return joinKind;
    }

    public String getTable() {
        return table;
    }

    public FieldInfo getField() {
        return field;
    }

    public String getLinkField() {
        return linkField;
    }

    public String getAlias() {
        return alias;
    }
}
