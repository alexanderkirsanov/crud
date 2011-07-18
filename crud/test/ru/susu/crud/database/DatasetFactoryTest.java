package ru.susu.crud.database;

import org.junit.After;
import org.junit.Test;
import ru.susu.crud.database.connection.ConnectionManager;
import ru.susu.crud.database.dataset.Dataset;
import ru.susu.crud.database.dataset.Field;
import ru.susu.crud.xml.XMLReader;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DatasetFactoryTest {

    private Statement statement;

    @Test
    public void getDatasetFactory() throws Exception {
        ConnectionProperties connectionProperties = new ConnectionProperties("localhost", "test", "dem", "s1234s", 3306);
        ConnectionManager connectionManager = new ConnectionManager(connectionProperties);
        statement = connectionManager.getConnection().createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS test(id INTEGER, "
                + "name VARCHAR(60), age INTEGER);");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS students(id INTEGER, "
                + "name VARCHAR(60), data DATE);");
        XMLReader xmlReader = new XMLReader("table.xml");
        DatasetFactory datasetFactory = new DatasetFactory(xmlReader.getTables(), connectionManager);
        Dataset studentsDataset = datasetFactory.getDataset("students");
        List<Field> studentsField = datasetFactory.getFields("students");
        String[] line = new String[3];
        line[0] = "1";
        line[1] = "alex";
        line[2] = "2012-12-12";
        Map<Field, String> mapOfValues = new HashMap<Field, String>();
        int i = 0;
        for (Field field : studentsField) {
            mapOfValues.put(field, line[i]);
            i++;
        }
        studentsDataset.insertData(mapOfValues);
        assertEquals(1, studentsDataset.getRowCount());
    }

    @After
    public void tearDown() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        ConnectionProperties connectionProperties = new ConnectionProperties("localhost", "test", "dem", "s1234s", 3306);
        ConnectionManager connectionManager = new ConnectionManager(connectionProperties);
        statement = connectionManager.getConnection().createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS test;");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS test(id INTEGER PRIMARY KEY, "
                + "name VARCHAR(60), age INTEGER);");
        statement.executeUpdate("DROP TABLE IF EXISTS students;");
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS students(id INTEGER, "
                + "name VARCHAR(60), data DATE);");
    }
}
