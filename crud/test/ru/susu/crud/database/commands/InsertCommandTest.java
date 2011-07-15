package ru.susu.crud.database.commands;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class InsertCommandTest {


    @Test
    public void createCommandTest() throws Exception {
        InsertCommand insertCommand = new InsertCommand("table");
        assertEquals("INSERT INTO table (id, name) VALUES ('1', 'name');", insertCommand.createCommand(new HashMap<String, String>() {{
            put("id", "1");
            put("name", "name");

        }}));
    }


}
