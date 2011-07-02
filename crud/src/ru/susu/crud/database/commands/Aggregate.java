package ru.susu.crud.database.commands;

public class Aggregate {

    public static final int AVERAGE = 1;
    public static final int SUM = 2;
    public static final int MAX = 3;
    public static final int MIN = 4;
    public static final int COUNT = 5;


    public static String toString(int aggregateConst) {
        return toSQL(aggregateConst);
    }

    public static String toSQL(int aggregateConst) {

        switch (aggregateConst) {
            case AVERAGE:
                return "AVG";
            case SUM:
                return "SUM";
            case MAX:
                return "MAX";
            case MIN:
                return "MIN";
            case COUNT:
                return "COUNT";
            default:
                return "";
        }

    }


}
