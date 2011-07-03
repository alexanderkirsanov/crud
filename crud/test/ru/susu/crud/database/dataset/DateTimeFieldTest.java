package ru.susu.crud.database.dataset;

import org.junit.Before;
import org.junit.Test;
import ru.susu.crud.database.commands.FieldType;

import java.util.Calendar;
import java.util.Date;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class DateTimeFieldTest {
    private DateTimeField field;

    @Before
    public void setUp() throws Exception {
        field = new DateTimeField("name", "alias", "sourceTable", false);
    }

    @Test
    public void getSqlValueTest() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2012, 11, 12, 1, 1, 1);
        Date date = ((Date) field.getSqlValue("2012-12-12 01:01:01"));
        assertTrue((calendar.getTimeInMillis() - date.getTime() < 1000));
    }

    @Test
    public void getEngFieldType() throws Exception {
        assertEquals(FieldType.DATE_TIME, field.getEngFieldType());
    }
}
