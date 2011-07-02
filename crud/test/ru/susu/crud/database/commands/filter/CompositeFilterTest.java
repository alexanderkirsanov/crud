package ru.susu.crud.database.commands.filter;

import org.junit.Before;
import org.junit.Test;
import ru.susu.crud.database.dataset.Field;

import static org.junit.Assert.assertEquals;

class MyField extends Field {
    private final String name;

    public MyField(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyField myField = (MyField) o;

        return !(name != null ? !name.equals(myField.name) : myField.name != null);

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

public class CompositeFilterTest {

    private String filterLinkType;
    private CompositeFilter compositeFilter;

    @Before
    public void setUp() {
        filterLinkType = "test";
        compositeFilter = new CompositeFilter(filterLinkType);
    }

    @Test
    public void InnerFiltersTest() throws Exception {
        MyField myField = new MyField("test");
        compositeFilter.addFilter(myField, FieldFilter.contains("test"));
        assertEquals(FieldFilter.contains("test"),compositeFilter.getInnerFilters().get(myField));
    }

    @Test
    public void getFilterLinkTypeTest() throws Exception {
        assertEquals(filterLinkType, compositeFilter.getFilterLinkType());
    }
}
