package ru.susu.crud.database.commands;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class InsertCommandTest {


    @Test
    public void createCommandTest() throws Exception {
        InsertCommand insertCommand = new InsertCommand("table");
        insertCommand.addParameters("id","1");
        insertCommand.addParameters("name","name");
        assertEquals("INSERT INTO table (id, name) VALUES (1, name);", insertCommand.createCommand());
    }


}
