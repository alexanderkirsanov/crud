package ru.susu.crud.database.dataset;

import ru.susu.crud.database.commands.FieldType;

public class BlobField extends Field {
    @Override
    public int getEngFieldType() {
        return FieldType.BLOB;
    }

    public BlobField(String name, String alias, String sourceTable, boolean autoincrement) {
        super(name, alias, sourceTable, autoincrement);
    }
}
