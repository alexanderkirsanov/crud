package ru.susu.crud.database.commands.filter;

import org.junit.Before;
import org.junit.Test;
import ru.susu.crud.database.dataset.IntegerField;
import ru.susu.crud.database.dataset.StringField;

import static org.junit.Assert.assertEquals;

public class FilterConditionGeneratorTest {

    private FilterConditionGenerator filterConditionGenerator;

    @Before
    public void setUp() {
        filterConditionGenerator = new FilterConditionGenerator();
    }

    @Test
    public void createConditionTest() throws Exception {

    }

    @Test
    public void visitContainsFieldFilterTest() throws Exception {
        FieldFilter fieldFilter = FieldFilter.contains("test");
        IntegerField integerField = new IntegerField("number", "", "table", false);
        assertEquals("UPPER(CAST(table.number AS CHAR)) LIKE ('%test%')", filterConditionGenerator.createCondition(fieldFilter, integerField));
        StringField stringField = new StringField("name", "", "table", false);
        assertEquals("UPPER(CAST(table.name AS CHAR)) LIKE ('%test%')", filterConditionGenerator.createCondition(fieldFilter, stringField));
    }

    @Test
    public void visitEqualsFieldFilterTest() throws Exception {
        FieldFilter fieldFilter = FieldFilter.equals("test");
        IntegerField integerField = new IntegerField("number", "", "table", false);
        assertEquals("CAST(table.number AS CHAR) = 'test'", filterConditionGenerator.createCondition(fieldFilter, integerField));
        StringField stringField = new StringField("name", "", "table", false);
        assertEquals("CAST(table.name AS CHAR) = 'test'", filterConditionGenerator.createCondition(fieldFilter, stringField));
    }

    @Test
    public void visitFieldNotEqualsConditionFieldFilterTest() throws Exception {
        FieldFilter fieldNotEqualsFilter = new FieldFilter("value", "<>");
        IntegerField integerField = new IntegerField("number", "", "table", false);
        assertEquals("table.number <> 'value'", filterConditionGenerator.createCondition(fieldNotEqualsFilter, integerField));
        StringField stringField = new StringField("name", "", "table", false);
        assertEquals("table.name <> 'value'", filterConditionGenerator.createCondition(fieldNotEqualsFilter, stringField));
    }

    @Test
    public void visitFieldSensitiveConditionFieldFilterTest() throws Exception {
        FieldFilter fieldSensitiveLikeFilter = new FieldFilter("value", "LIKE");
        IntegerField integerField = new IntegerField("number", "", "table", false);
        assertEquals("CAST(table.number AS CHAR) LIKE 'value'", filterConditionGenerator.createCondition(fieldSensitiveLikeFilter, integerField));
        StringField stringField = new StringField("name", "", "table", false);
        assertEquals("CAST(table.name AS CHAR) LIKE 'value'", filterConditionGenerator.createCondition(fieldSensitiveLikeFilter, stringField));
    }

    @Test
    public void visitNoPredicateFilterTest() throws Exception {
        FieldFilter fieldSensitiveLikeFilter = new FieldFilter("10", "LIKE");
        IntegerField integerField = new IntegerField("number", "", "table", false);
        NoPredicateFilter noPredicateFilter = new NoPredicateFilter(fieldSensitiveLikeFilter);
        assertEquals("NOT (CAST(table.number AS CHAR) LIKE '10')", filterConditionGenerator.createCondition(noPredicateFilter, integerField));
    }

    @Test
    public void visitBetweenFieldFilterTest() throws Exception {
        BetweenFilter betweenFilter = new BetweenFilter("10", "20");
        IntegerField integerField = new IntegerField("number", "", "table", false);
        assertEquals("(table.number BETWEEN 10 AND 20)", filterConditionGenerator.createCondition(betweenFilter, integerField));

    }

    @Test
    public void visitIsNullFieldFilterTest() throws Exception {
        IsNullFieldFilter isNullFieldFilter = new IsNullFieldFilter();
        StringField integerField = new StringField("name", "", "table", false);
        assertEquals("table.name IS NULL", filterConditionGenerator.createCondition(isNullFieldFilter, integerField));
    }
}
