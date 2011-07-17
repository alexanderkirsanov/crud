package ru.susu.crud.database.commands;

import com.sun.istack.internal.Nullable;
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
    public void setUp(){
        nameField = new StringField("name", "", "test", false);
        ageField = new IntegerField("age", "", "test", false);
        listOfField = new LinkedList<Field>() {{
            add(new IntegerField("id", "", "test", true));
            add(nameField);
            add(ageField);
        }};


    }
    @Test
    public void createCommandTest() throws Exception {
        Map<Field, Filterable> mapOfFilters = new HashMap<Field, Filterable>();
        FieldFilter fieldFilter = FieldFilter.equals("ivanov");
        mapOfFilters.put(nameField, fieldFilter);
        SelectCommand selectCommand = new SelectCommand("test");
        assertEquals("SELECT id, name, age FROM test WHERE CAST(test.name AS CHAR) = 'ivanov' ORDER BY age ASC",
                selectCommand.createCommand(listOfField, mapOfFilters, ageField, "ASC"));
    }

        @Test
    public void createSimpleCommandTest() throws Exception {
        SelectCommand selectCommand = new SelectCommand("test");
        assertEquals("SELECT id, name, age FROM test",
                selectCommand.createCommand(listOfField, null, null, ""));
    }

    @Test
    public void createOnlyOrderCommandTest() throws Exception {
        SelectCommand selectCommand = new SelectCommand("test");
        assertEquals("SELECT id, name, age FROM test ORDER BY age ASC",
                selectCommand.createCommand(listOfField, null, ageField, "ASC"));
    }

}
