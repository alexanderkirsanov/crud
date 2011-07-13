package ru.susu.crud.database.commands.filter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FieldFilterTest {

    private String value;
    private String testType;
    private FieldFilter fieldFilter;

    @Before
    public void setUp() {
        value = "test";
        testType = "testType";
        fieldFilter = new FieldFilter(value, testType);
    }

    @Test
    public void containsTest() throws Exception {
        FieldFilter fieldFilter = FieldFilter.contains("test");
        assertEquals("ILIKE", fieldFilter.getFilterType());
        assertEquals("true", fieldFilter.getIgnoreFieldDataType());
        assertEquals("%test%", fieldFilter.getValue());

    }


    @Test
    public void equalsTest() throws Exception {
        FieldFilter fieldFilter = FieldFilter.equals("test");
        assertEquals("=", fieldFilter.getFilterType());
        assertEquals("test", fieldFilter.getIgnoreFieldDataType());
        assertEquals("test", fieldFilter.getValue());
    }

    @Test
    public void getValueTest() throws Exception {
        assertEquals(value, fieldFilter.getValue());
    }

    @Test
    public void getFilterTypeTest() throws Exception {
        assertEquals(testType, fieldFilter.getFilterType());
    }

    @Test
    public void getIgnoreFieldDataTypeTest() throws Exception {
        assertEquals("false", fieldFilter.getIgnoreFieldDataType());
    }
}
