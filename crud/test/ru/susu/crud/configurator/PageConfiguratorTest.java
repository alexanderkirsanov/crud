package ru.susu.crud.configurator;

import org.junit.Test;
import ru.susu.crud.TestConnectionProperties;
import ru.susu.crud.database.connection.ConnectionManager;
import ru.susu.crud.database.dataset.DateField;
import ru.susu.crud.database.dataset.Field;
import ru.susu.crud.database.dataset.IntegerField;
import ru.susu.crud.database.dataset.StringField;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PageConfiguratorTest {
    @Test
    public void configureTest() throws Exception {
        IPage iPage = mock(IPage.class);
        PageConfigurator pageConfigurator = new PageConfigurator(iPage);
        pageConfigurator.configure();
        ConnectionManager connectionManager = new ConnectionManager(TestConnectionProperties.getConnectionProperties());
        verify(iPage).setConnectionManager(connectionManager);
        final String testTableName = "test";
        List<Field> testFieldList = new LinkedList<Field>() {
            {
                add(new IntegerField("id", "", testTableName, true));
                add(new StringField("name", "", testTableName, false));
                add(new IntegerField("age", "", testTableName, true));
            }
        };

        verify(iPage).addFields(testTableName, testFieldList);
        final String studentsTableName = "students";
        List<Field> studentsFieldList = new LinkedList<Field>() {
            {
                add(new IntegerField("id", "", studentsTableName, true));
                add(new StringField("name", "", studentsTableName, false));
                add(new DateField("data", "", studentsTableName, true));
            }
        };

        verify(iPage).addFields(studentsTableName, studentsFieldList);


    }
}
