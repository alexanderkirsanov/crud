package ru.susu.crud.database.commands;

import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class EngCommandImp {
    public EngCommandImp() {
        //TODO: implement constructor
    }

    protected String createCaseSensitiveLikeExpression(String left, String right) {
        return new StringBuilder(left).append(" LIKE ").append(right).toString();
    }

    protected String createCaseInsensitiveLikeExpression(String left, String right) {
        return new StringBuilder("UPPER(").append(left).append(") LIKE (").append(right).append(")").toString();
    }

    public String getCaseSensitiveLikeExpression(FieldInfo field, String value) {
        return createCaseSensitiveLikeExpression(getCastedToCharFieldExpression(field), getValueAsSQLString(value));
    }

    public String getCaseInsensitiveLikeExpression(FieldInfo field, String value) {
        return createCaseInsensitiveLikeExpression(getCastedToCharFieldExpression(field), getValueAsSQLString(value));
    }

    //TODO:FilterCondition generator && Connection factory;

    protected String getDateTimeFieldAsSQLForSelect(FieldInfo fieldInfo) {
        return getFieldFullName(fieldInfo);
    }

    protected String getDateFieldAsSQLForSelect(FieldInfo fieldInfo) {
        return getFieldFullName(fieldInfo);
    }

    protected String getTimeFieldAsSQLForSelect(FieldInfo fieldInfo) {
        return getFieldFullName(fieldInfo);
    }

    public String getAliasedAsFieldExpression(String expression, String alias) {
        return new StringBuilder(expression).append(" AS ").append(alias).toString();
    }

    public String getFieldAsSQLInSelectFieldList(FieldInfo fieldInfo) {
        String result;
        if (fieldInfo.getFieldType() == FieldType.DATE_TIME) {
            result = getDateTimeFieldAsSQLForSelect(fieldInfo);
        } else if (fieldInfo.getFieldType() == FieldType.DATE) {
            result = getDateFieldAsSQLForSelect(fieldInfo);
        } else if (fieldInfo.getFieldType() == FieldType.TIME) {
            result = getTimeFieldAsSQLForSelect(fieldInfo);
        } else {
            result = getFieldFullName(fieldInfo);
        }

        if ((fieldInfo.getAlias() != null) && fieldInfo.getAlias().length() > 0) {
            result = getAliasedAsFieldExpression(result, fieldInfo.getAlias());
        } else {
            result = getAliasedAsFieldExpression(result, fieldInfo.getName());
        }
        return result;
    }


    public String getValueAsSQLString(String value) {
        return new StringBuilder("'").append(escapeString(value)).append("'").toString();
    }

    public String escapeString(String string) {
        return string.replace("'", "''");
    }

    public String getCastToCharExpression(String value) {
        return new StringBuilder("CAST(").append(value).append(" AS CHAR)").toString();
    }

    public String getCastedToCharFieldExpression(FieldInfo fieldInfo) {
        return this.getCastToCharExpression(getFieldFullName(fieldInfo));
    }

    public String getFieldFullName(FieldInfo fieldInfo) {
        if ((fieldInfo.getTableName() != null) && (fieldInfo.getTableName().length() > 0)) {
            return new StringBuilder(fieldInfo.getTableName()).append(".").append(fieldInfo.getName()).toString();
        } else {
            return fieldInfo.getName();
        }
    }

    protected String getDateTimeFieldValueAsSQL(FieldInfo fieldInfo, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new StringBuilder("'").append(simpleDateFormat.format(date)).append("'").toString();
    }

    protected String getDateFieldValueAsSQL(FieldInfo fieldInfo, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return new StringBuilder("'").append(simpleDateFormat.format(date)).append("'").toString();
    }

    protected String getTimeFieldValueAsSQL(FieldInfo fieldInfo, Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return new StringBuilder("'").append(simpleDateFormat.format(date)).append("'").toString();
    }

    public String getFieldValueAsSQL(FieldInfo fieldInfo, String value) throws Exception {
        if (fieldInfo.getFieldType() == FieldType.NUMBER) {
            if (value.contains(".") || value.contains(",")) {
                Double aDouble = Double.parseDouble(value);
                return aDouble.toString().replace(',', '.');
            } else {
                Integer aInteger = Integer.parseInt(value);
                return aInteger.toString();
            }
        } else if (fieldInfo.getFieldType() == FieldType.DATE_TIME) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            formatter.parse((String) value);
        } else if (fieldInfo.getFieldType() == FieldType.TIME) {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            formatter.parse((String) value);
        } else if (fieldInfo.getFieldType() == FieldType.DATE) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatter.parse((String) value);
        } else if (fieldInfo.getFieldType() == FieldType.BLOB) {
            RandomAccessFile f = new RandomAccessFile((String) value, "r");
            byte[] arr = new byte[(int) f.length()];
            f.readFully(arr);
            return new StringBuilder("'").append(Arrays.toString(arr)).append("'").toString();
        }
        return new StringBuilder("'").append(escapeString((value.toString()))).append("'").toString();
    }

    public String getFieldValueForInsert(FieldInfo fieldInfo, String value, boolean setToDefault) throws Exception {
        if (setToDefault)
            return "DEFAULT";
        if (value == null || ((String) value).length() == 0) {
            return "NULL";
        }

        if ((fieldInfo.getFieldType() == FieldType.NUMBER)) {

            if ("0".equals(value)) return "NULL";

        }
        return getFieldValueAsSQL(fieldInfo, value);
    }


    public String getFieldValueForDelete(FieldInfo fieldInfo, String value) throws Exception {
        if (value == null || ((String) value).length() == 0) {
            return "NULL";
        }

        if ((fieldInfo.getFieldType() == FieldType.NUMBER)) {
            if ("0".equals(value)) return "NULL";
        }
        return getFieldValueAsSQL(fieldInfo, value);
    }


    public String getSetFieldValueClause(FieldInfo fieldInfo, String value, String defaultValue) throws Exception {
        return getFieldFullName(fieldInfo) + " = " + getDateTimeFieldAsSQLForUpdate(fieldInfo, value, defaultValue);
    }

    public String getDateTimeFieldAsSQLForUpdate(FieldInfo fieldInfo, String value, String defaultValue) throws Exception {
        if (value == null || ((String) value).length() == 0) {
            if (defaultValue != null) {
                return "DEFAULT";
            }
            return "NULL";
        }

        if ((fieldInfo.getFieldType() == FieldType.NUMBER)) {
            if ("0".equals(value)) return "NULL";
        }
        return getFieldValueAsSQL(fieldInfo, value);
    }

    public String getLimitClause(String limitCount, String upLimit) {
        return new StringBuilder("LIMIT ").append(upLimit).append(",").append(limitCount).toString();
    }

    private String getJoinKindAsSQL(int joinKind) {
        switch (joinKind) {
            case JoinKind.INNER:
                return "INNER JOIN";
            case JoinKind.LEFT_OUTER:
                return "LEFT OUTER JOIN";
            default:
                return "JOIN";
        }
    }

    public String createJoinClause(JoinInfo joinInfo) {
        return new StringBuilder(getJoinKindAsSQL(joinInfo.getJoinKind())).
                append(" ").append(joinInfo.getTable()).
                append(
                        joinInfo.getAlias().length() > 0 ?
                                (" " + joinInfo.getAlias())
                                : ""
                ).
                append(" ON ").append(

                (joinInfo.getAlias() != null) ?
                        joinInfo.getAlias()
                        : joinInfo.getTable())

                .append(".").append(joinInfo.getLinkField()).
                        append(" = ").append(joinInfo.getField().getName()).toString();

    }

    public String getIsNullCondition(String fieldName) {
        return fieldName + " IS NULL";
    }


}
