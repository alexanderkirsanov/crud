package ru.susu.crud.database.dataset;

import ru.susu.crud.database.commands.FieldType;

public class IntegerField extends Field {
    @Override
    public int getEngFieldType() {
        return FieldType.NUMBER;
    }

    public IntegerField(String name, String alias, String sourceTable, boolean autoincrement) {
        super(name, alias, sourceTable, autoincrement);
    }
}
