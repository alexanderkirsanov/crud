package ru.susu.crud.database.dataset;

import ru.susu.crud.database.commands.FieldType;

public class BooleanField extends Field {
    @Override
    public int getEngFieldType() {
        return FieldType.BOOLEAN;
    }

    public BooleanField(String name, String alias, String sourceTable, boolean autoincrement) {
        super(name, alias, sourceTable, autoincrement);
    }
}
