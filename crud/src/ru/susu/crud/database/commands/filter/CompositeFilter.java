package ru.susu.crud.database.commands.filter;


import ru.susu.crud.database.dataset.Field;

import java.util.HashMap;
import java.util.Map;

public class CompositeFilter implements Filterable {
    private final String filterLinkType;
    private Map<Field, Filterable> innerFilters = new HashMap<Field, Filterable>();

    public CompositeFilter(String filterLinkType) {
        this.filterLinkType = filterLinkType;
    }

    public Map<Field, Filterable> getInnerFilters() {
        return innerFilters;
    }

    public String getFilterLinkType() {
        return filterLinkType;
    }

    public void addFilter(Field field, Filterable filter) {
        this.innerFilters.put(field, filter);
    }

    public void accept(Visitable filterVisitor) {
        filterVisitor.visitComponentFilter(this);
    }
}
