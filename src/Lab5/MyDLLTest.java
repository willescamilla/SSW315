package Lab5;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyDLLTest {

	@Test
	public void testMyDLLImpl() {
		MyDLLImpl<Integer> temp = new MyDLLImpl<Integer>();
		assertTrue(temp.isEmpty());
	}

	@Test
	public void testInsert() {
		MyDLLImpl<Integer> temp = new MyDLLImpl<Integer>();
		temp.insert(10);
		assertFalse(temp.isEmpty());
		assertEquals(1, temp.getSize());
	}

	@Test
	public void testDelete() {
		MyDLLImpl<Integer> temp = new MyDLLImpl<Integer>();
		temp.delete(10);
		assertTrue(temp.isEmpty());
		temp.insert(10);
		temp.insert(25);
		temp.insert(30);
		temp.delete(99);
		assertEquals(3, temp.getSize());
		temp.delete(25);
		assertEquals(2, temp.getSize());
	}
}
