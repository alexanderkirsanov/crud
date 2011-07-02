package ru.susu.crud.database.commands.filter;

public interface Filterable {
    void accept(Visitable filterVisitor);
}
