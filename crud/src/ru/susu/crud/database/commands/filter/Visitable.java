package ru.susu.crud.database.commands.filter;

public interface Visitable {
    void visitFieldFilter(FieldFilter fieldFilter);

    void visitNoPredicateFilter(NoPredicateFilter noPredicateFilter);

    void visitBetweenFieldFilter(BetweenFilter betweenFilter);

    void visitComponentFilter(CompositeFilter compositeFilter);

    void visitIsNullFieldFilter(IsNullFieldFilter isNullFieldFilter);
}
