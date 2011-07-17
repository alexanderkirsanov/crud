package ru.susu.crud.database.commands.filter;

public interface Filterable {
    String accept(Visitable filterVisitor) throws Exception;
}
