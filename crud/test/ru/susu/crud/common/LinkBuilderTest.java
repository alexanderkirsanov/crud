package ru.susu.crud.common;


import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class LinkBuilderTest {

    private LinkBuilder linkBuilder;
    private Map<String, String> parameters;

    @Before
    public void setUp() {
        String page = "test.jsp";
        linkBuilder = new LinkBuilder(page);
        linkBuilder.addParameter("name", "test");
        linkBuilder.addParameter("id", "3");
        parameters = new HashMap<String, String>();
        parameters.put("xxx", "test");
        parameters.put("help", "yes");
        parameters.put("test", "5");
    }

    @Test
    public void parameterTest() throws Exception {
        assertEquals("test.jsp?id=3&name=test&", linkBuilder.getLink());
    }

    @Test
    public void removeParameterTest() throws Exception {
        linkBuilder.removeParameter("id");
        assertEquals("test.jsp?name=test&", linkBuilder.getLink());
    }

    @Test
    public void getParametersTest() throws Exception {
        linkBuilder.addParameters(parameters);
        assertEquals(5, linkBuilder.getParameters().size());
    }

    @Test
    public void cloneTest() throws Exception {
        assertEquals(linkBuilder, linkBuilder.clone());
    }
}
