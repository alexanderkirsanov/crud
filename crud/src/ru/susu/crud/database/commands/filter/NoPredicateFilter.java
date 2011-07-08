package ru.susu.crud.database.commands.filter;

public class NoPredicateFilter implements Filterable {
    private final Filterable innerFilter;

    public NoPredicateFilter(Filterable innerFilter) {
        this.innerFilter = innerFilter;
    }

    public Filterable getInnerFilter() {
        return innerFilter;
    }

    public void accept(Visitable filterVisitor) {
        filterVisitor.visitNoPredicateFilter(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoPredicateFilter that = (NoPredicateFilter) o;

        return !(innerFilter != null ? !innerFilter.equals(that.innerFilter) : that.innerFilter != null);

    }

    @Override
    public int hashCode() {
        return innerFilter != null ? innerFilter.hashCode() : 0;
    }
}
