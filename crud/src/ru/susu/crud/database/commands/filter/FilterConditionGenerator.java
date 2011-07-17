package ru.susu.crud.database.commands.filter;

import ru.susu.crud.database.commands.EngCommandImp;
import ru.susu.crud.database.commands.FieldType;
import ru.susu.crud.database.dataset.Field;

public class FilterConditionGenerator implements Visitable {


    private EngCommandImp engCommandImp;
    private String resultCondition;
    private Field field;

    public FilterConditionGenerator() {
        engCommandImp = new EngCommandImp();
    }

    public String createCondition(Filterable fieldFilter, Field field) throws Exception {
        this.field = field;
        return fieldFilter.accept(this);
    }

    public String visitFieldFilter(FieldFilter fieldFilter) {
        resultCondition = "";
        if (!fieldFilter.getIgnoreFieldDataType().equals("false")) {
            if (fieldFilter.getFilterType().equals("LIKE")) {
                resultCondition = engCommandImp.getCaseSensitiveLikeExpression(
                        engCommandImp.getFieldInfo(this.field),
                        fieldFilter.getValue()
                );
            } else if (fieldFilter.getFilterType().equals("ILIKE")) {
                resultCondition = engCommandImp.getCaseInsensitiveLikeExpression(
                        engCommandImp.getFieldInfo(this.field),
                        fieldFilter.getValue()
                );
            } else {
                resultCondition =
                        new StringBuilder(engCommandImp.getCastedToCharFieldExpression(engCommandImp.getFieldInfo(this.field)))
                                .append(" ")
                                .append(fieldFilter.getFilterType())
                                .append(" ")
                                .append(engCommandImp.getValueAsSQLString(fieldFilter.getValue())).toString();
            }
        } else {
            String value = fieldFilter.getValue();
            if (value.isEmpty() && field.getEngFieldType() == FieldType.NUMBER) {
                if (fieldFilter.getFilterType().equals("=")) {
                    resultCondition = engCommandImp.getIsNullCondition(engCommandImp.getFieldFullName(engCommandImp.getFieldInfo(this.field)));
                } else if (fieldFilter.getFilterType().equals("<>")) {
                    resultCondition = new StringBuilder("NOT (")
                            .append(engCommandImp.getIsNullCondition(
                                    engCommandImp.getFieldFullName(engCommandImp.getFieldInfo(this.field)))
                            ).append(")").toString();

                }
            } else if (!value.isEmpty()) {
                if (fieldFilter.getFilterType().equals("LIKE")) {
                    resultCondition = engCommandImp.getCaseSensitiveLikeExpression(
                            engCommandImp.getFieldInfo(field),
                            fieldFilter.getValue()
                    );
                } else if (fieldFilter.getFilterType().equals("ILIKE")) {
                    resultCondition = engCommandImp.getCaseInsensitiveLikeExpression(
                            engCommandImp.getFieldInfo(field),
                            fieldFilter.getValue()
                    );
                } else {
                    resultCondition =
                            new StringBuilder(engCommandImp.getFieldFullName(engCommandImp.getFieldInfo(this.field)))
                                    .append(" ")
                                    .append(fieldFilter.getFilterType())
                                    .append(" ")
                                    .append(engCommandImp.getValueAsSQLString(fieldFilter.getValue())).toString();
                }
            } else {
                if (fieldFilter.getFilterType().equals("=")) {
                    resultCondition = engCommandImp.getIsNullCondition(engCommandImp.getFieldFullName(engCommandImp.getFieldInfo(this.field)));
                } else if (fieldFilter.getFilterType().equals("<>")) {
                    resultCondition = new StringBuilder("NOT (")
                            .append(engCommandImp.getIsNullCondition(engCommandImp.getFieldFullName(engCommandImp.getFieldInfo(field))))
                            .append(")").toString();
                }
            }
        }
        return resultCondition;
    }

    public String visitNoPredicateFilter(NoPredicateFilter noPredicateFilter) throws Exception {
        resultCondition = "";
        resultCondition = new StringBuilder("NOT (")
                .append(this.createCondition(noPredicateFilter.getInnerFilter(), this.field))
                .append(")").toString();
        return resultCondition;
    }

    public String visitBetweenFieldFilter(BetweenFilter betweenFilter) throws Exception {
        resultCondition = "";
        resultCondition = new StringBuilder("(").append(engCommandImp.getFieldFullName(engCommandImp.getFieldInfo(this.field)))
                .append(" BETWEEN ")
                .append(engCommandImp.getFieldValueAsSQL(engCommandImp.getFieldInfo(this.field), betweenFilter.getStartValue()))
                .append(" AND ")
                .append(engCommandImp.getFieldValueAsSQL(engCommandImp.getFieldInfo(this.field), betweenFilter.getEndValue()))
                .append(")").toString();
        return resultCondition;
    }

    public String visitIsNullFieldFilter(IsNullFieldFilter isNullFieldFilter) {
        resultCondition = "";
        resultCondition = engCommandImp.getIsNullCondition(engCommandImp.getFieldFullName(engCommandImp.getFieldInfo(this.field)));
        return resultCondition;
    }
}
