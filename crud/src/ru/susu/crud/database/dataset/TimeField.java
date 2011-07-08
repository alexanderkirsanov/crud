package ru.susu.crud.database.dataset;

import ru.susu.crud.database.commands.FieldType;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimeField extends Field {

    private SimpleDateFormat simpleDateFormat;

    public TimeField(String name, String alias, String sourceTable, boolean autoincrement) {
        this(name, "HH:mm:ss", alias, sourceTable, autoincrement);
    }

    public TimeField(String name, String timeFormat, String alias, String sourceTable, boolean autoincrement) {
        super(name, alias, sourceTable, autoincrement);
        simpleDateFormat = new SimpleDateFormat(timeFormat);
    }

    @Override
    public Object getSqlValue(String sql) throws ParseException {
        return simpleDateFormat.parse(sql);
    }

    @Override
    public int getEngFieldType() {
        return FieldType.TIME;
    }
}
