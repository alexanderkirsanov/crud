package ru.susu.crud.components.editors;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

//TODO ExtractsValueFromPost for Editors!!!
public class EditorsTest {

    private Object testValue;
    private String testName;

    @Before
    public void setUp() {
        this.testValue = new Object();
        this.testName = "testName";
    }

    @Test
    public void textEditCreateTest() {
        TextEdit testTE = new TextEdit(testName);
        assertEquals(testTE.getName(), testName);
    }

    @Test
    public void textEditConfigureTest() {
        TextEdit testTE = new TextEdit(testName, 10, 10);
        assertEquals(testTE.getName(), testName);
        assertEquals(testTE.getSize(), 10);
        assertEquals(testTE.getPasswordMode(), false);
        assertEquals(testTE.getMaxLength(), 10);
        testTE.setSize(25);
        testTE.setMaxLength(30);
        testTE.setPasswordMode(true);
        assertEquals(testTE.getSize(), 25);
        assertEquals(testTE.getMaxLength(), 30);
        assertEquals(testTE.getPasswordMode(), true);
    }

    @Test
    public void textEditGetValueAndAllowHtmlCharactersTest() {
        TextEdit testTE = new TextEdit(testName, 1, 1);
        assertEquals(testTE.getValue(), null);
        assertEquals(testTE.getAllowHtmlCharacters(), true);
        testTE.setValue(testValue);
        testTE.setAllowHtmlCharacters(false);
        assertEquals(testTE.getValue(), testValue);
        assertEquals(testTE.getAllowHtmlCharacters(), false);
    }

    @Test
    public void textAreaEditCreateAndConfigureTest() {
        TextAreaEdit testTAE = new TextAreaEdit(testName, 10, 10);
        assertEquals(testTAE.getName(), testName);
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
        TimeEdit testTimeEdit = new TimeEdit(testName);
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

    @Test
    public void maskedEditCreateAndConfigureTest() {
        String mask = "mask";
        String hint = "hint";
        MaskedEdit testME = new MaskedEdit(testName, mask, hint);
        assertEquals(testME.getName(), testName);
        assertEquals(testME.getMask(), mask);
        assertEquals(testME.getHint(), hint);
    }

    @Test
    public void spinEditCreateAndConfigureTest() {
        SpinEdit testSE = new SpinEdit(testName);
        double testMinValue = 0.0;
        double testMaxValue = 0.0;
        assertEquals(testSE.isUseConstraints(), false);
        assertEquals(testSE.getMaxValue(), testMaxValue);
        assertEquals(testSE.getMinValue(), testMinValue);
        testSE.setUseConstraints(true);
        testMinValue = 10;
        testMaxValue = 13;
        testSE.setMaxValue(testMaxValue);
        testSE.setMinValue(testMinValue);
        assertEquals(testSE.isUseConstraints(), true);
        assertEquals(testSE.getMaxValue(), testMaxValue);
        assertEquals(testSE.getMinValue(), testMinValue);
    }

    @Test
    public void checkBoxCreateAndConfigureTest() {
        CheckBox testCB = new CheckBox(testName);
        assertEquals(testCB.checked(), false);
        testCB.setValue(testValue);
        assertEquals(testCB.checked(), true);
    }

    /*@Test
    public void dateTimeEditCreateAndConfigureTest() {
        DateTimeEdit testDTE = new DateTimeEdit(testName, false);
        assertEquals(testDTE.getFormat(), EditorsConf.getInstance().dateWithoutTimePattern);
        testDTE.setShowsTime(true);
        assertEquals(testDTE.getFormat(), EditorsConf.getInstance().dateWithTimePattern);
    } */


}
