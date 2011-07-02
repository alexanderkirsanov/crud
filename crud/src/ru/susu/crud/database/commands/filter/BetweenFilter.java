package ru.susu.crud.database.commands.filter;

public class BetweenFilter implements Filterable {
    private final String startValue;
    private final String endValue;

    public BetweenFilter(String startValue, String endValue) {
        this.startValue = startValue;
        this.endValue = endValue;
    }

    public String getEndValue() {
        return endValue;
    }


    public String getStartValue() {
        return startValue;
    }

    public void accept(Visitable filterVisitor) {
        filterVisitor.visitBetweenFieldFilter(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BetweenFilter that = (BetweenFilter) o;

        return !(endValue != null ? !endValue.equals(that.endValue) : that.endValue != null) && !(startValue != null ? !startValue.equals(that.startValue) : that.startValue != null);

    }

    @Override
    public int hashCode() {
        int result = startValue != null ? startValue.hashCode() : 0;
        result = 31 * result + (endValue != null ? endValue.hashCode() : 0);
        return result;
    }
}
