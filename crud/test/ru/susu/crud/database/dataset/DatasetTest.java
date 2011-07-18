package ru.susu.crud.database.dataset;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.susu.crud.database.ConnectionProperties;
import ru.susu.crud.database.connection.ConnectionManager;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DatasetTest {

    private Dataset dataset;
    private Statement statement;

    @Before
    public void setUp() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        ConnectionProperties connectionProperties = new ConnectionProperties("localhost", "test", "dem", "s1234s", 3306);
        ConnectionManager connectionManager = new ConnectionManager(connectionProperties);
        statement = connectionManager.getConnection().createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS test(id INTEGER PRIMARY KEY, "
                + "name VARCHAR(60), age INTEGER);");

        dataset = new Dataset(new LinkedList<Field>() {{
            add(new IntegerField("id", "", "test", true));
            add(new StringField("name", "", "test", false));
            add(new IntegerField("age", "", "test", false));
        }}, connectionManager);
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

    @Test
    public void insertDataTest() throws Exception {
        Map<Field, String> mapOfData = new HashMap<Field, String>() {
            {
                put(new IntegerField("id", "", "test", true), "1");
                put(new StringField("name", "", "test", false), "alex");
                put(new IntegerField("age", "", "test", true), "23");
            }
        };
        dataset.insertData(mapOfData);
        assertEquals(1, dataset.getRowCount());
    }

    @Test
    public void updateDataTest() throws Exception {
                Map<Field, String> mapOfData = new HashMap<Field, String>() {
            {
                put(new IntegerField("id", "", "test", true), "1");
                put(new StringField("name", "", "test", false), "alex");
                put(new IntegerField("age", "", "test", true), "23");
            }
        };
        dataset.insertData(mapOfData);
        Map<Field, String> mapOfNewData = new HashMap<Field, String>() {
            {
                put(new IntegerField("id", "", "test", true), "1");
                put(new StringField("name", "", "test", false), "Ivan");
                put(new IntegerField("age", "", "test", true), "23");
            }
        };
        dataset.updateData(0, mapOfNewData);
        assertTrue(Arrays.toString(dataset.getLine(0)).contains("Ivan"));
    }

        @Test
    public void deleteDataTest() throws Exception {
                Map<Field, String> mapOfData = new HashMap<Field, String>() {
            {
                put(new IntegerField("id", "", "test", true), "1");
                put(new StringField("name", "", "test", false), "alex");
                put(new IntegerField("age", "", "test", true), "23");
            }
        };
            dataset.insertData(mapOfData);
            assertEquals(1,dataset.getRowCount());
        dataset.deleteData(0);
        assertEquals(0,dataset.getRowCount());
    }

    @After
    public void tearDown() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        ConnectionProperties connectionProperties = new ConnectionProperties("localhost", "test", "dem", "s1234s", 3306);
        ConnectionManager connectionManager = new ConnectionManager(connectionProperties);
        statement = connectionManager.getConnection().createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS test;");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS test(id INTEGER PRIMARY KEY, "
                + "name VARCHAR(60), age INTEGER);");
    }
}
