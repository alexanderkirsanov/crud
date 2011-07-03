package ru.susu.crud.database.dataset;

import org.junit.Test;
import ru.susu.crud.database.commands.FieldType;

import static org.junit.Assert.assertEquals;

public class IntegerFieldTest {
    @Test
    public void getEngFieldTypeTest() throws Exception {
        assertEquals(FieldType.NUMBER, new IntegerField("name", "alias", "sourceTable", false).getEngFieldType());
    }
}
