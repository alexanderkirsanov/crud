package ru.susu.crud.database.dataset;

import java.text.ParseException;

public abstract class Field {
    private String name;
    private String alias;
    private String sourceTable;
    private boolean isAutoincrement;
    private boolean readOnly;
    private String defaultValue;


    public Field(String name, String alias, String sourceTable, boolean autoincrement) {
        this.name = name;
        this.alias = alias;
        this.sourceTable = sourceTable;
        isAutoincrement = autoincrement;
        this.readOnly = false;
        this.defaultValue = null;
    }

    public void setReadOnly(String defautValue, boolean readOnly) {
        this.readOnly = readOnly;
        this.defaultValue = defautValue;
    }


    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public String getSourceTable() {
        return sourceTable;
    }

    public void setSourceTable(String sourceTable) {
        this.sourceTable = sourceTable;
    }

    public String getNameInDataset() {
        return this.getAlias().length() == 0 ? this.getName() : this.getAlias();
    }

    public boolean isAutoincrement() {
        return isAutoincrement;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public Object getSqlValue(String sql) throws ParseException {
        return sql;
    }

    public abstract int getEngFieldType();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        return !(alias != null ? !alias.equals(field.alias) : field.alias != null) && !(name != null ? !name.equals(field.name) : field.name != null)
                && !(sourceTable != null ? !sourceTable.equals(field.sourceTable) : field.sourceTable != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (alias != null ? alias.hashCode() : 0);
        result = 31 * result + (sourceTable != null ? sourceTable.hashCode() : 0);
        return result;
    }
}
