package ru.susu.crud.database.commands;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AggregateTest {
    @Test
    public void toStringTest() throws Exception {
        assertEquals("COUNT", Aggregate.toString(Aggregate.COUNT));
        assertEquals("MAX", Aggregate.toString(Aggregate.MAX));
        assertEquals("MIN", Aggregate.toString(Aggregate.MIN));
        assertEquals("SUM", Aggregate.toString(Aggregate.SUM));
        assertEquals("AVG", Aggregate.toString(Aggregate.AVERAGE));
    }

    @Test
    public void testToSQL() throws Exception {
        assertEquals("COUNT", Aggregate.toSQL(Aggregate.COUNT));
        assertEquals("MAX", Aggregate.toSQL(Aggregate.MAX));
        assertEquals("MIN", Aggregate.toSQL(Aggregate.MIN));
        assertEquals("SUM", Aggregate.toSQL(Aggregate.SUM));
        assertEquals("AVG", Aggregate.toSQL(Aggregate.AVERAGE));
    }
}
