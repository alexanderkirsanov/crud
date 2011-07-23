package ru.susu.crud.database.commands;

import org.junit.Before;
import org.junit.Test;
import ru.susu.crud.database.commands.filter.FieldFilter;
import ru.susu.crud.database.commands.filter.Filterable;
import ru.susu.crud.database.dataset.Field;
import ru.susu.crud.database.dataset.IntegerField;
import ru.susu.crud.database.dataset.StringField;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class SelectCommandTest {

    private StringField nameField;
    private IntegerField ageField;
    private LinkedList<Field> listOfField;

    @Before
    public void setUp() {
        nameField = new StringField("name", "", "test", false);
        ageField = new IntegerField("age", "", "test", false);
        listOfField = new LinkedList<Field>() {{
            add(new IntegerField("id", "", "test", true));
            add(nameField);
            add(ageField);
        }};


    }

    @Test
    public void limitTest() {
        SelectCommand selectCommand = new SelectCommand("test", listOfField);
        selectCommand.setLimitCount(1);
        assertEquals(1, selectCommand.getLimitCount());
        selectCommand.setUpLimit(2);
        assertEquals(2, selectCommand.getUpLimit());
    }

    @Test
    public void createCommandTest() throws Exception {
        Map<Field, Filterable> mapOfFilters = new HashMap<Field, Filterable>();
        FieldFilter fieldFilter = FieldFilter.equals("ivanov");
        SelectCommand selectCommand = new SelectCommand("test", listOfField);
        selectCommand.addFieldFilter(nameField, fieldFilter);
        selectCommand.addOrderBy(ageField, "ASC");
        assertEquals("SELECT id, name, age FROM test WHERE CAST(test.name AS CHAR) = 'ivanov' ORDER BY age ASC ",
                selectCommand.createCommand());
    }

    @Test
    public void createSimpleCommandTest() throws Exception {
        SelectCommand selectCommand = new SelectCommand("test", listOfField);
        assertEquals("SELECT id, name, age FROM test ",
                selectCommand.createCommand());
    }

    @Test
    public void createOnlyOrderCommandTest() throws Exception {
        SelectCommand selectCommand = new SelectCommand("test", listOfField);
        selectCommand.addOrderBy(ageField, "ASC");
        assertEquals("SELECT id, name, age FROM test ORDER BY age ASC ",
                selectCommand.createCommand());
    }

    @Test
    public void createOnlyLimitCommandTest() throws Exception {
        SelectCommand selectCommand = new SelectCommand("test", listOfField);
        selectCommand.setLimitCount(1);
        selectCommand.setUpLimit(1);
        assertEquals("SELECT id, name, age FROM test LIMIT 1,1",
                selectCommand.createCommand());
    }

}
