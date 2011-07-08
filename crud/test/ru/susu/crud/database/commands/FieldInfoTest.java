package ru.susu.crud.database.commands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FieldInfoTest {

    FieldInfo fieldInfo;
    String tableName = "testTable";
    String name = "testField";
    int fieldType = FieldType.BLOB;
    String alias = "Alias";


    @Before
    public void setUp() {
        fieldInfo = new FieldInfo(tableName, name, fieldType, alias);
    }

    @Test
    public void nameTest() throws Exception {
        assertEquals(name, fieldInfo.getName());
        String newName = "newName";
        fieldInfo.setName(newName);
        assertEquals(newName,fieldInfo.getName());
    }

    @Test
    public void tableNameTest() throws Exception {
        assertEquals(tableName, fieldInfo.getTableName());
        String newTableName = "newTableName";
        fieldInfo.setTableName(newTableName);
        assertEquals(newTableName,fieldInfo.getTableName());
    }

    @Test
    public void getFieldTypeTest() throws Exception {
        assertEquals(fieldType, fieldInfo.getFieldType());
        int newFieldType = FieldType.BOOLEAN;
        fieldInfo.setFieldType(newFieldType);
        assertEquals(newFieldType,fieldInfo.getFieldType());
    }

    @Test
    public void getAliasTest() throws Exception {
        assertEquals(alias, fieldInfo.getAlias());
        String newAlias = "newAlias";
        fieldInfo.setAlias(newAlias);
        assertEquals(newAlias,fieldInfo.getAlias());
    }
}
