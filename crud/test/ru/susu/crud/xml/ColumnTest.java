package ru.susu.crud.xml;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ColumnTest {

    private Column column;

    @Before
    public void setUp() {
        column = new Column();
    }

    @Test
    public void nameTest() throws Exception {
        String name = "name";
        column.setName(name);
        assertEquals(name, column.getName());
    }

    @Test
    public void getTypeTest() throws Exception {
        String type = "integer";
        column.setType(type);
        assertEquals(type, column.getType());
    }

    @Test
    public void nullTest() throws Exception {
        boolean aNull = true;
        column.setNull(aNull);
        assertTrue(column.isNull());
    }

    @Test
    public void sizeTest() throws Exception {
        int size = 10;
        column.setSize(size);
        assertEquals(size, column.getSize());
    }
}
