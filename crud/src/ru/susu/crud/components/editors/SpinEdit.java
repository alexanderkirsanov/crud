package ru.susu.crud.components.editors;

public class SpinEdit extends CustomEditor {
    private boolean useConstraints;
    private double maxValue;
    private double minValue;

    public SpinEdit(String testName) {
        super(testName);
    }

    public boolean isUseConstraints() {
        return useConstraints;
    }

    public void setUseConstraints(boolean useConstraints) {
        this.useConstraints = useConstraints;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }
}
