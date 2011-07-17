package ru.susu.crud.database.commands.filter;

public class FieldFilter implements Filterable {
    private final String value;
    private final String filterType;
    private String ignoreFieldDataType = "false";

    public static FieldFilter contains(String value) {
        return new FieldFilter(new StringBuilder("%").append(value).append("%").toString(), "ILIKE", "true");
    }

    public static FieldFilter equals(String value) {
        return new FieldFilter(value, "=", value);
    }

    public FieldFilter(String value, String filterType) {
        this(value, filterType, "false");
    }

    public FieldFilter(String value, String filterType, String ignoreFieldDataType) {
        this.value = value;
        this.filterType = filterType;
        this.ignoreFieldDataType = ignoreFieldDataType;
    }

    public String getValue() {
        return value;
    }

    public String getFilterType() {
        return filterType;
    }

    public String getIgnoreFieldDataType() {
        return ignoreFieldDataType;
    }

    public String accept(Visitable filterVisitor) {
        return filterVisitor.visitFieldFilter(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FieldFilter that = (FieldFilter) o;

        return !(filterType != null ? !filterType.equals(that.filterType) : that.filterType != null) && !(value != null ? !value.equals(that.value) : that.value != null);

    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (filterType != null ? filterType.hashCode() : 0);
        return result;
    }
}
