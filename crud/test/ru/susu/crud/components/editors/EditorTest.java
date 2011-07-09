package ru.susu.crud.components.editors;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

//TODO ExtractsValueFromPost for Editors!!!
public class EditorTest {

    @Test
    public void textEditCreateTest() {
        String name = "name";
        TextEdit testTE = new TextEdit(name);
        assertEquals(testTE.getName(), name);
    }

    @Test
    public void textEditConfigureTest() {
        TextEdit testTE = new TextEdit("name", 10, 10);
        assertEquals(testTE.getName(), "name");
        assertEquals(testTE.getSize(), 10);
        assertEquals(testTE.getPasswordMode(), false);
        assertEquals(testTE.getMaxLength(), 10);
        testTE.setSize(25);
        testTE.setMaxLength(30);
        testTE.setPasswordMode(true);
        assertEquals(testTE.getSize(), 25);
        assertEquals(testTE.getMaxLength(),30);
        assertEquals(testTE.getPasswordMode(), true);
    }

    @Test
    public void textEditGetValueAndAllowHtmlCharactersTest() {
        TextEdit testTE = new TextEdit("name", 1, 1);
        Object testValue = new Object();
        assertEquals(testTE.getValue(), null);
        assertEquals(testTE.getAllowHtmlCharacters(), true);
        testTE.setValue(testValue);
        testTE.setAllowHtmlCharacters(false);
        assertEquals(testTE.getValue(), testValue);
        assertEquals(testTE.getAllowHtmlCharacters(), false);
    }
}
