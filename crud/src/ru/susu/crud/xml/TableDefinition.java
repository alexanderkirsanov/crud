package ru.susu.crud.xml;

import java.util.ArrayList;
import java.util.List;

public class TableDefinition {
    private final String name;
    private List<Column> columns = new ArrayList<Column>();

    public TableDefinition(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void addColumn(Column column) {
        this.columns.add(column);
    }

    @Override
    public String toString() {
        return "TableDefinition{" +
                "name='" + name + '\'' +
                ", columns=" + columns +
                '}';
    }
}
