package ru.susu.crud.components.editors;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

//TODO ExtractsValueFromPost for Editors!!!
public class EditorsTest {

    private Object testValue;

    @Before
    public void setUp() {
        this.testValue = new Object();
    }

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
        assertEquals(testTE.getValue(), null);
        assertEquals(testTE.getAllowHtmlCharacters(), true);
        testTE.setValue(testValue);
        testTE.setAllowHtmlCharacters(false);
        assertEquals(testTE.getValue(), testValue);
        assertEquals(testTE.getAllowHtmlCharacters(), false);
    }

    @Test
    public void textAreaEditCreateAndConfigureTest() {
        TextAreaEdit testTAE = new TextAreaEdit("name", 10, 10);
        assertEquals(testTAE.getName(), "name");
        assertEquals(testTAE.getColumnCount(), 10);
        assertEquals(testTAE.getRowCount(), 10);
        assertEquals(testTAE.getAllowHtmlCharacters(), true);
        assertEquals(testTAE.getValue(), null);
        testTAE.setColumnCount(15);
        testTAE.setRowCount(13);
        testTAE.setAllowHtmlCharacters(false);
        testTAE.setValue(testValue);
        assertEquals(testTAE.getColumnCount(), 15);
        assertEquals(testTAE.getRowCount(), 13);
        assertEquals(testTAE.getAllowHtmlCharacters(), false);
        assertEquals(testTAE.getValue(), testValue);
    }

    @Test
    public void timeEditCreateAndConfigureTest() {
        TimeEdit testTimeEdit = new TimeEdit("name");
        assertEquals(testTimeEdit.getValue(), null);
        assertEquals(testTimeEdit.getCustomAttributes(), null);
        assertEquals(testTimeEdit.isReadOnly(), false);
        assertEquals(testTimeEdit.getFieldName(), null);
        testTimeEdit.setValue(testValue);
        testTimeEdit.setReadOnly(true);
        String testCustomAttributes = "12";
        testTimeEdit.setCustomAttributes(testCustomAttributes);
        String testFieldName = "fieldName";
        testTimeEdit.setFieldName(testFieldName);
        assertEquals(testTimeEdit.getValue(), testValue);
        assertEquals(testTimeEdit.isReadOnly(), true);
        assertEquals(testTimeEdit.getCustomAttributes(), testCustomAttributes);
        assertEquals(testTimeEdit.getFieldName(), testFieldName);
    }


}
