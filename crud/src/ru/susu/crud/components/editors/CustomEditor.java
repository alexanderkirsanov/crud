package ru.susu.crud.components.editors;

import ru.susu.crud.components.*;

public abstract class CustomEditor extends Component {
    private String name;
    private Object value;
    private boolean readOnly;
    private String customAttributes;
    private String fieldName;

    public CustomEditor(String name) {
        super(name);
        this.customAttributes = null;
    }


    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getCustomAttributes() {
        return customAttributes;
    }

    public void setCustomAttributes(String customAttributes) {
        this.customAttributes = customAttributes;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
