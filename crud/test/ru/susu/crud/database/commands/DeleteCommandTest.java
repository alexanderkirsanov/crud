package ru.susu.crud.database.commands;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DeleteCommandTest {
    @Test
    public void createCommandTest() throws Exception {
        DeleteCommand deleteCommand = new DeleteCommand("table");
        Map<String, String> parameters = new HashMap<String, String>() {{
            put("id", "12");
            put("name", "ivan");
            put("surname", "ivanov");
        }};

        assertEquals("DELETE FROM table WHERE id = 12 AND name = ivan AND surname = ivanov", deleteCommand.createCommand(parameters));

        Map<String, String> simpleParameters = new HashMap<String, String>() {{
            put("id", "12");
        }};

        assertEquals("DELETE FROM table WHERE id = 12",deleteCommand.createCommand(simpleParameters));

    }
}
