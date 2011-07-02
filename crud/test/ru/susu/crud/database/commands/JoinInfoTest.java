package ru.susu.crud.database.commands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JoinInfoTest {

    private String table;
    private FieldInfo fieldInfo;
    private String linkField;
    private String alias;
    private JoinInfo joinInfo;
    private int inner;

    @Before
    public void setUp(){
        fieldInfo = new FieldInfo("testTable","name", FieldType.BLOB,"alias");
        inner = JoinKind.INNER;
        table = "table";
        linkField = "linkField";
        alias = "alias";
        joinInfo = new JoinInfo(inner, table, fieldInfo, linkField, alias);
    }
    @Test
    public void getJoinKindTest() throws Exception {
        assertEquals(inner,joinInfo.getJoinKind());
    }

    @Test
    public void getTableTest() throws Exception {
        assertEquals(table,joinInfo.getTable());
    }

    @Test
    public void getFieldTest() throws Exception {
        assertEquals(fieldInfo,joinInfo.getField());
    }

    @Test
    public void getLinkFieldTest() throws Exception {
        assertEquals(linkField,joinInfo.getLinkField());
    }

    @Test
    public void getAliasTest() throws Exception {
        assertEquals(alias,joinInfo.getAlias());
    }
}
