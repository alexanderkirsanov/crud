package ru.susu.crud.database.commands.filter;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NoPredicateFilterTest {
    @Test
    public void getInnerFilterTest() throws Exception {
        NoPredicateFilter noPredicateFilter= new NoPredicateFilter(FieldFilter.equals("test"));
        assertEquals(FieldFilter.equals("test"), noPredicateFilter.getInnerFilter());
    }


}
