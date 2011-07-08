package ru.susu.crud.database.dataset;

import org.junit.Before;
import org.junit.Test;
import ru.susu.crud.database.commands.FieldType;

import java.util.Calendar;
import java.util.Date;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class TimeFieldTest {
    private TimeField field;

    @Before
    public void setUp() throws Exception {
        field = new TimeField("name", "alias", "sourceTable", false);
    }

    @Test
    public void getSqlValueTest() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, 1, 1);
        Date date = ((Date) field.getSqlValue("01:01:01"));
        assertTrue((calendar.getTimeInMillis() - date.getTime() < 1000));
    }

    @Test
    public void getEngFieldType() throws Exception {
        assertEquals(FieldType.TIME, field.getEngFieldType());
    }
}
