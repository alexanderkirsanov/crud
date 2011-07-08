package ru.susu.crud.database.dataset;

import org.junit.Test;
import ru.susu.crud.database.commands.FieldType;

import static org.junit.Assert.assertEquals;

public class BooleanFieldTest {
    @Test
    public void getEngFieldType() throws Exception {
        assertEquals(FieldType.BOOLEAN, new BooleanField("name", "alias", "sourceTable", false).getEngFieldType());
    }
}
