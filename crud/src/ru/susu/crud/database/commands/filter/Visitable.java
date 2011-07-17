package ru.susu.crud.database.commands.filter;

public interface Visitable {
    String visitFieldFilter(FieldFilter fieldFilter);

    String visitNoPredicateFilter(NoPredicateFilter noPredicateFilter) throws Exception;

    String visitBetweenFieldFilter(BetweenFilter betweenFilter) throws Exception;

    String visitIsNullFieldFilter(IsNullFieldFilter isNullFieldFilter);


}
