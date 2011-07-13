package ru.susu.crud.database.commands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EngCommandImpTest {

    private EngCommandImp engCommandImp;
    private FieldInfo fieldInfo;

    @Before
    public void setUp() {
        engCommandImp = new EngCommandImp();
        fieldInfo = new FieldInfo("test", "testField", FieldType.NUMBER, "alias");
    }

    @Test
    public void getCaseSensitiveLikeExpressionTest() throws Exception {
        assertEquals("CAST(test.testField AS CHAR) LIKE '1'", engCommandImp.getCaseSensitiveLikeExpression(fieldInfo, "1"));
    }

    @Test
    public void getCaseInsensitiveLikeExpressionTest() throws Exception {
        assertEquals("UPPER(CAST(test.testField AS CHAR)) LIKE ('1')", engCommandImp.getCaseInsensitiveLikeExpression(fieldInfo, "1"));
    }

    @Test
    public void getAliasedAsFieldExpressionTest() throws Exception {
        assertEquals("expression AS field", engCommandImp.getAliasedAsFieldExpression("expression", "field"));
    }

    @Test
    public void getFieldAsSQLInSelectFieldListTest() throws Exception {
        assertEquals("test.testField AS alias", engCommandImp.getFieldAsSQLInSelectFieldList(fieldInfo));
    }

    @Test
    public void getValueAsSQLStringTest() throws Exception {
        assertEquals("'value'", engCommandImp.getValueAsSQLString("value"));
    }

    @Test
    public void escapeStringTest() throws Exception {
        assertEquals("tr''tr", engCommandImp.escapeString("tr'tr"));
    }

    @Test
    public void getCastToCharExpressionTest() throws Exception {
        assertEquals("CAST(fff AS CHAR)", engCommandImp.getCastToCharExpression("fff"));
    }

    @Test
    public void getCastedToCharFieldExpressionTest() throws Exception {
        assertEquals("CAST(test.testField AS CHAR)", engCommandImp.getCastedToCharFieldExpression(fieldInfo));
    }

    @Test
    public void getFieldFullNameTest() throws Exception {
        assertEquals("CAST(test.testField AS CHAR)", engCommandImp.getCastedToCharFieldExpression(fieldInfo));
    }

    @Test
    public void getFieldValueAsSQLTest() throws Exception {
        assertEquals("12", engCommandImp.getFieldValueAsSQL(fieldInfo, "12"));
        assertEquals("12.5", engCommandImp.getFieldValueAsSQL(fieldInfo, "12.5"));
        assertEquals("'12-12-2012'", engCommandImp.getFieldValueAsSQL(new FieldInfo("test", "testField", FieldType.DATE, "alias"), "12-12-2012"));
        assertEquals("'12:12:12'", engCommandImp.getFieldValueAsSQL(new FieldInfo("test", "testField", FieldType.TIME, "alias"), "12:12:12"));
        assertEquals("'12-12-2012 12:12:12'", engCommandImp.getFieldValueAsSQL(new FieldInfo("test", "testField", FieldType.DATE_TIME, "alias"), "12-12-2012 12:12:12"));
    }

    @Test
    public void getFieldValueForInsertTest() throws Exception {
        assertEquals("12", engCommandImp.getFieldValueForInsert(fieldInfo, "12", false));
        assertEquals("NULL", engCommandImp.getFieldValueForInsert(fieldInfo, "0", false));
        assertEquals("DEFAULT", engCommandImp.getFieldValueForInsert(fieldInfo, "0", true));
    }

    @Test
    public void getFieldValueForDeleteTest() throws Exception {
        assertEquals("12", engCommandImp.getFieldValueForDelete(fieldInfo, "12"));
        assertEquals("NULL", engCommandImp.getFieldValueForDelete(fieldInfo, "0"));
    }

    @Test
    public void getSetFieldValueClauseTest() throws Exception {
        assertEquals("test.testField = 12", engCommandImp.getSetFieldValueClause(fieldInfo, "12", "23"));
        assertEquals("test.testField = DEFAULT", engCommandImp.getSetFieldValueClause(fieldInfo, "", "23"));
    }

    @Test
    public void getDateTimeFieldAsSQLForUpdateTest() throws Exception {
        assertEquals("12", engCommandImp.getDateTimeFieldAsSQLForUpdate(fieldInfo, "12", "20"));
        assertEquals("DEFAULT", engCommandImp.getDateTimeFieldAsSQLForUpdate(fieldInfo, "", "23"));
    }

    @Test
    public void getLimitClauseTest() throws Exception {
        assertEquals("LIMIT 12,12", engCommandImp.getLimitClause("12", "12"));
    }

    @Test
    public void createJoinClauseTest() throws Exception {
        JoinInfo joinInfo = new JoinInfo(JoinKind.INNER, "table", fieldInfo, "field", "alias");
        assertEquals("INNER JOIN table alias ON alias.field = testField", engCommandImp.createJoinClause(joinInfo));
        JoinInfo outterJoinInfo = new JoinInfo(JoinKind.LEFT_OUTER, "table", fieldInfo, "field", "alias");
        assertEquals("LEFT OUTER JOIN table alias ON alias.field = testField", engCommandImp.createJoinClause(outterJoinInfo));

    }

    @Test
    public void getIsNullConditionTest() throws Exception {
       assertEquals("field IS NULL", engCommandImp.getIsNullCondition("field"));
    }
}
