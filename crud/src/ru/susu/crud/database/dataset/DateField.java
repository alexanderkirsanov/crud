package ru.susu.crud.database.dataset;

import ru.susu.crud.database.commands.FieldType;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateField extends Field {
    private SimpleDateFormat simpleDateFormat;

    public DateField(String name, String alias, String sourceTable, boolean autoincrement) {
        this(name, "yyyy-MM-dd", alias, sourceTable, autoincrement);
    }

    public DateField(String name, String dateFormat, String alias, String sourceTable, boolean autoincrement) {
        super(name, alias, sourceTable, autoincrement);
        simpleDateFormat = new SimpleDateFormat(dateFormat);
    }

    @Override
    public Object getSqlValue(String sql) throws ParseException {
        return  simpleDateFormat.parse(sql);
    }

    @Override
    public int getEngFieldType() {
        return FieldType.DATE;
    }
}
