package ru.susu.crud.database.commands.filter;

public class IsNullFieldFilter implements Filterable {

    public void accept(Visitable filterVisitor) {
        filterVisitor.visitIsNullFieldFilter(this);
    }
}
