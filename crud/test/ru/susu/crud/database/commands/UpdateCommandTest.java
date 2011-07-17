package ru.susu.crud.database.commands;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class UpdateCommandTest {
    @Test
    public void createCommandTest() throws Exception {
        UpdateCommand updateCommand = new UpdateCommand("test");
        Map<String, String> actualParameters = new HashMap<String, String>() {{
            put("id", "12");
            put("name", "ivan");
            put("surname", "ivanov");
        }};

        Map<String, String> oldParameters = new HashMap<String, String>() {{
            put("id", "12");
            put("name", "petr");
            put("surname", "petrov");
        }};

        assertEquals("UPDATE test SET id = '12', name = 'ivan', surname = 'ivanov' WHERE 'id = '12' AND name = 'petr' AND surname = 'petrov'",
                updateCommand.createCommand(oldParameters, actualParameters));

        UpdateCommand simpleUpdateCommand = new UpdateCommand("test");
        Map<String, String> simpleActualParameters = new HashMap<String, String>() {{
            put("id", "12");
        }};

        Map<String, String> simpleOldParameters = new HashMap<String, String>() {{
            put("id", "13");
        }};
        assertEquals("UPDATE test SET id = '12' WHERE 'id = '13'",
                        updateCommand.createCommand(simpleOldParameters, simpleActualParameters));
    }
}
