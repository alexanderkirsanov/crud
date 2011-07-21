package ru.susu.crud.editor;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TextEditorTest {

    private TextEditor textEditor;

    @Before
    public void setUp() {
        textEditor = new TextEditor();
    }

    @Test
    public void getDefinitionTest() throws Exception {
        Map<String, String[]> definition = textEditor.getDefinition();
        assertEquals("TextEditor", definition.get("type")[0]);
        assertEquals("10", definition.get("size")[0]);
    }

    @Test
    public void getSizeTest() throws Exception {
        assertEquals(10, textEditor.getSize());
        textEditor = new TextEditor(20);
        assertEquals(20, textEditor.getSize());
    }
}
