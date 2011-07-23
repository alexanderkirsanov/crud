package ru.susu.crud.database.commands;

import org.junit.Test;
import ru.susu.crud.database.dataset.IntegerField;
import ru.susu.crud.database.dataset.StringField;

import static org.junit.Assert.assertEquals;

public class DeleteCommandTest {
    @Test
    public void createCommandTest() throws Exception {
        DeleteCommand deleteCommand = new DeleteCommand("table");
        StringField nameField = new StringField("name", "", "test", false);
        StringField surnameField = new StringField("surname", "", "test", false);
        IntegerField idField = new IntegerField("id", "", "test", false);
        deleteCommand.setParameters(idField, "12");
        deleteCommand.setParameters(nameField, "ivan");
        deleteCommand.setParameters(surnameField, "ivanov");

        assertEquals("DELETE FROM table WHERE test.name = ivan AND test.surname = ivanov AND test.id = 12", deleteCommand.createCommand());

        DeleteCommand simpleDeleteCommand = new DeleteCommand("table");
        simpleDeleteCommand.setParameters(idField, "12");

        assertEquals("DELETE FROM table WHERE test.id = 12", simpleDeleteCommand.createCommand());

    }
}
