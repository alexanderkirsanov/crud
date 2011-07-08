package ru.susu.crud.database.dataset;

import ru.susu.crud.database.commands.FieldType;

public class StringField extends Field {

    @Override
    public int getEngFieldType() {
        return FieldType.STRING;
    }

    public StringField(String name, String alias, String sourceTable, boolean autoincrement) {
        super(name, alias, sourceTable, autoincrement);
    }
}
