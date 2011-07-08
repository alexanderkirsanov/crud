package ru.susu.crud.components;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class ComponentsTest {
	Component testComponent;
	
	@Before
	public void setUp(){
		testComponent = new Component("1");
	}
	
	@Test
	public void testCreateComponent(){
		assertEquals(testComponent.getName(),"1");
	}
	
	@Test
	public void testGetAllowNullValue(){
		assertTrue(testComponent.getAllowNullValue());
	}
	
	@Test
	public void testSetAllowNullValue(){
		testComponent.setAllowNullValue(false);
		assertFalse(testComponent.getAllowNullValue());
	}
	
	@Test
	public void testCreateTextBox(){
		Component testTextBox = new TextBox("1");
		assertEquals(testTextBox.getName(), "1");
	}

	
}