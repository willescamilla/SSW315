package Lab11;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class QuickSortTest {

	@Test
	void test1() {
		QuicksortLab qs = new QuicksortLab();
		int[] temp = { 12, 3, 9, 6, 7, 8, 2, 1 };
		String expectedOutput = "[1, 2, 3, 8, 7, 6, 9, 12]";
		qs.quickSort(temp);
		assertEquals(expectedOutput, Arrays.toString(qs.items));
	}
	
	@Test
	void test2() {
		QuicksortLab qs = new QuicksortLab();
		int[] temp = { 99, 3, 23, 7, 7, 10, 5, 88 };
		String expectedOutput = "[3, 5, 7, 7, 10, 23, 88, 99]";
		qs.quickSort(temp);
		assertEquals(expectedOutput, Arrays.toString(qs.items));
	}

}
