package ru.susu.crud.database.dataset;

import org.junit.Before;
import org.junit.Test;
import ru.susu.crud.database.commands.FieldType;

import java.util.Calendar;
import java.util.Date;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class DateFieldTest {

    private DateField field;

    @Before
    public void setUp() throws Exception {
        field = new DateField("name", "alias", "sourceTable", false);
    }

    @Test
    public void getSqlValueTest() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2012, 11, 12, 0, 0, 0);
        Date date = ((Date) field.getSqlValue("2012-12-12"));
        assertTrue((calendar.getTimeInMillis() - date.getTime() < 1000));
    }

    @Test
    public void getEngFieldType() throws Exception {
        assertEquals(FieldType.DATE, field.getEngFieldType());
    }
}
