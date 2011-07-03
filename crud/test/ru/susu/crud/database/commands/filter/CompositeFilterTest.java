package ru.susu.crud.database.commands.filter;

import org.junit.Before;
import org.junit.Test;
import ru.susu.crud.database.dataset.Field;

import static org.junit.Assert.assertEquals;

class MyField extends Field {


    public MyField(String name, String alias, String sourceTable, boolean autoincrement) {
        super(name, alias, sourceTable, autoincrement);
    }

    @Override
    public int getEngFieldType() {
        return 0;
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
        MyField myField = new MyField("test", "alias", "table", false);
        compositeFilter.addFilter(myField, FieldFilter.contains("test"));
        assertEquals(FieldFilter.contains("test"), compositeFilter.getInnerFilters().get(myField));
    }

    @Test
    public void getFilterLinkTypeTest() throws Exception {
        assertEquals(filterLinkType, compositeFilter.getFilterLinkType());
    }
}
