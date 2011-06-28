package ru.susu.crud;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ru.susu.crud.components.Component;

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

	
}
