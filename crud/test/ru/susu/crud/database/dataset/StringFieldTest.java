package ru.susu.crud.database.dataset;

import org.junit.Test;
import ru.susu.crud.database.commands.FieldType;

import static org.junit.Assert.assertEquals;

public class StringFieldTest {
    @Test
    public void getEngFieldType() throws Exception {
        assertEquals(FieldType.STRING, new StringField("name", "alias", "sourceTable", false).getEngFieldType());
    }
}
