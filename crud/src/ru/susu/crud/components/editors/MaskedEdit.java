package ru.susu.crud.components.editors;

public class MaskedEdit extends CustomEditor {

    private String mask;
    private String hint;

    public MaskedEdit(String testName, String mask, String hint) {
        super(testName);
        this.mask = mask;
        this.hint = hint;
    }

    public String getHint() {
        return hint;
    }

    public String getMask() {
        return mask;
    }
}
