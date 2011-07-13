package ru.susu.crud.database.dataset;

import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

public class DatasetTest {

    private Dataset dataset;

    @Before
    public void setUp() {
        dataset = new Dataset(new LinkedList<Field>() {{
            add(new IntegerField("id", "", "test", true));
            add(new StringField("name", "", "test", false));
            add(new IntegerField("age", "", "test", false));
        }});
    }

    @Test
    public void insertLineTest() throws Exception {
        dataset.insertLine(new String[]{"1", "alex", "12"});
        assertEquals(1, dataset.getRowCount());
        assertEquals("1", dataset.getLine(0)[0]);

    }

    @Test
    public void clearTest() throws Exception {
        dataset.insertLine(new String[]{"1", "alex", "12"});
        assertEquals(1, dataset.getRowCount());
        dataset.clear();
        assertEquals(0, dataset.getRowCount());
    }

    @Test
    public void removeLineTest() throws Exception {
        dataset.insertLine(new String[]{"1", "alex", "12"});
        dataset.insertLine(new String[]{"2", "ivan", "121"});
        assertEquals(2, dataset.getRowCount());
        dataset.removeLine(0);
        assertEquals(1, dataset.getRowCount());
    }

}
