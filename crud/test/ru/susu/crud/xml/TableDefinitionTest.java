package ru.susu.crud.xml;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TableDefinitionTest {

    private String table;
    private TableDefinition tableDefinition;

    @Before
    public void setUp() {
        table = "table";
        tableDefinition = new TableDefinition(table);
    }

    @Test
    public void nameTest() throws Exception {
        assertEquals(table, tableDefinition.getName());
    }

    @Test
    public void columnsTest() throws Exception {
        Column column = new Column();
        tableDefinition.addColumn(column);
        assertEquals(1, tableDefinition.getColumns().size());
    }
}
