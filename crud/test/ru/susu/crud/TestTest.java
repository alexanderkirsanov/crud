package ru.susu.crud;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTest {
	@Test
	public void test(){
		Main main = new Main();
		assertEquals(1,main.a());
	}
	
	@Test
	public void testByDem(){
		assertTrue(true);
	}
}
