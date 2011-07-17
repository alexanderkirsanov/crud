package ru.susu.crud.database.commands.filter;

public class IsNullFieldFilter implements Filterable {

    public String accept(Visitable filterVisitor) {
        return  filterVisitor.visitIsNullFieldFilter(this);
    }
}
