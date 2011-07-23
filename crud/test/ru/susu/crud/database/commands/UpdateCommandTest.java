package ru.susu.crud.database.commands;

import org.junit.Test;
import ru.susu.crud.database.dataset.IntegerField;
import ru.susu.crud.database.dataset.StringField;

import static org.junit.Assert.assertEquals;

public class UpdateCommandTest {


    @Test
    public void createCommandTest() throws Exception {
        UpdateCommand updateCommand = new UpdateCommand("test");
        StringField nameField = new StringField("name", "", "test", false);
        StringField surnameField = new StringField("surname", "", "test", false);
        IntegerField idField = new IntegerField("id", "", "test", false);
        updateCommand.setParameters(idField, "12", "12");
        updateCommand.setParameters(nameField, "petr", "ivan");
        updateCommand.setParameters(surnameField, "petrov", "ivanov");

        assertEquals("UPDATE test SET test.name = ivan, test.surname = ivanov, test.id = 12 WHERE test.name = petr AND test.surname = petrov AND test.id = 12",
                updateCommand.createCommand());

        UpdateCommand simpleUpdateCommand = new UpdateCommand("test");
        simpleUpdateCommand.setParameters(idField, "13", "12");

        assertEquals("UPDATE test SET test.id = 12 WHERE test.id = 13",
                simpleUpdateCommand.createCommand());
    }
}
