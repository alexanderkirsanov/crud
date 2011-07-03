package ru.susu.crud.database.dataset;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FieldTest {

    private String name;
    private String alias;
    private String sourceTable;
    private boolean autoincrement;
    private MyField myField;

    class MyField extends Field {

        public MyField(String name, String alias, String sourceTable, boolean autoincrement) {
            super(name, alias, sourceTable, autoincrement);
        }

        @Override
        public int getEngFieldType() {
            return 0;
        }
    }

    @Before
    public void setUp() {
        name = "name";
        alias = "alias";
        sourceTable = "sourceTable";
        autoincrement = true;
        myField = new MyField(name, alias, sourceTable, autoincrement);
    }

    @Test
    public void readOnlyTest() throws Exception {
        boolean readOnly = true;
        myField.setReadOnly("", readOnly);
        assertEquals(readOnly, myField.isReadOnly());
    }

    @Test
    public void getNameTest() throws Exception {
        assertEquals(name, myField.getName());
    }

    @Test
    public void getAliasTest() throws Exception {
        assertEquals(alias, myField.getAlias());
    }

    @Test
    public void sourceTableTest() throws Exception {
        assertEquals(sourceTable, myField.getSourceTable());
    }

    @Test
    public void getNameInDatasetTest() throws Exception {
        assertEquals(alias, myField.getNameInDataset());
    }

    @Test
    public void isAutoincrementTest() throws Exception {
        assertEquals(autoincrement, myField.isAutoincrement());
    }

    @Test
    public void defaultValueTest() throws Exception {
        String defaultValue = "test";
        myField.setReadOnly(defaultValue, true);
        assertEquals(defaultValue, myField.getDefaultValue());
    }

    @Test
    public void getSqlValueTest() throws Exception {
        String sqlValue = "10";
        assertEquals(sqlValue, myField.getSqlValue(sqlValue ));
    }

}
