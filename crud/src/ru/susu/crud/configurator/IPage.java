package ru.susu.crud.configurator;

import ru.susu.crud.database.connection.ConnectionManager;
import ru.susu.crud.database.dataset.Field;
import ru.susu.crud.editor.Editor;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: lqip32
 * Date: 19.07.11
 * Time: 23:59
 * To change this template use File | Settings | File Templates.
 */
public interface IPage {
    void setConnectionManager(ConnectionManager connectionManager);

    void addFields(String table, List<Field> fields);

    void addEditors(String table, Map<String, Editor> editorsMap);
}
