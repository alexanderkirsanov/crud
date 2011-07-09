package ru.susu.crud.components.editors;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: dem
 * Date: 09.07.11
 * Time: 15:36
 * To change this template use File | Settings | File Templates.
 */
public class EditorTest {

    @Test
    public void textEditCreateTest() {
        String name = "name";
        TextEdit testTE = new TextEdit(name);
        assertEquals(testTE.getName(), name);
    }
}
