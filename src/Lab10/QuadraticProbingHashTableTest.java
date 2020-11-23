package Lab10;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;

import org.junit.jupiter.api.Test;

class QuadraticProbingHashTableTest {

	@Test
	void testNums() {
		QuadraticProbingHashTable H = new QuadraticProbingHashTable(21);
		MyInteger num1 = new MyInteger(7);
		MyInteger num2 = new MyInteger(22);
		MyInteger num3 = new MyInteger(44);
		MyInteger num4 = new MyInteger(43);
		MyInteger num5 = new MyInteger(27);
		MyInteger num6 = new MyInteger(89);
		MyInteger num7 = new MyInteger(30);
		MyInteger num8 = new MyInteger(64);
		MyInteger num9 = new MyInteger(85);
		H.insert(num1);
		H.insert(num2);
		H.insert(num3);
		H.insert(num4);
		H.insert(num5);
		H.insert(num6);
		H.insert(num7);
		H.insert(num8);
		H.insert(num9);
		HashEntry[] temp1 = H.getTable();
		assertTrue(num1.equals(temp1[7].element));
		assertTrue(num2.equals(temp1[1].element));
		assertTrue(num3.equals(temp1[2].element));
		assertTrue(num4.equals(temp1[5].element));
		assertTrue(num5.equals(temp1[6].element));
		assertTrue(num6.equals(temp1[9].element));
		assertTrue(num7.equals(temp1[10].element));
		assertTrue(num8.equals(temp1[17].element));
		assertTrue(num9.equals(temp1[16].element));
		System.out.println("Num test # of probes: " + H.numProbes);
	}
	
	@Test
	void testStrings() {
		QuadraticProbingHashTable Z = new QuadraticProbingHashTable(23);
		MyString str1 = new MyString("A");
		MyString str2 = new MyString("W");
		MyString str3 = new MyString("X");
		MyString str4 = new MyString("Y");
		MyString str5 = new MyString("CM");
		MyString str6 = new MyString("CK");
		MyString str7 = new MyString("BD");
		MyString str8 = new MyString("H");
		MyString str9 = new MyString("MOB");
		MyString str10 = new MyString("BD");
		Z.insert(str1);
		Z.insert(str2);
		Z.insert(str3);
		Z.insert(str4);
		Z.insert(str5);
		Z.insert(str6);
		Z.insert(str7);
		Z.insert(str8);
		Z.insert(str9);
		Z.insert(str10);
		HashEntry[] temp2 = Z.getTable();
		assertTrue(str1.equals(temp2[19].element));
		assertTrue(str2.equals(temp2[18].element));
		assertTrue(str3.equals(temp2[20].element));
		assertTrue(str4.equals(temp2[21].element));
		assertTrue(str5.equals(temp2[3].element));
		assertTrue(str6.equals(temp2[1].element));
		assertTrue(str7.equals(temp2[4].element));
		assertTrue(str8.equals(temp2[7].element));
		assertTrue(str9.equals(temp2[12].element));
		System.out.println("String test # of probes: " + Z.numProbes);
	}
	
	@Test
	void testDataSets() throws FileNotFoundException {
		QuadraticProbingHashTable ordered_dictionary = new QuadraticProbingHashTable(90804);
		QuadraticProbingHashTable huckleberry = new QuadraticProbingHashTable(90804);
		QuadraticProbingHashTable unorder_dictionary = new QuadraticProbingHashTable(90804);
		FileReader fr = new FileReader("linuxwords.txt");
		Scanner scan = new Scanner(fr);
		while(scan.hasNext()){
			ordered_dictionary.insert(new MyString(scan.nextLine()));
		}
		
		fr = new FileReader("huckleberry_short.txt");
		scan = new Scanner(fr);
		while(scan.hasNext()) {
			huckleberry.insert(new MyString(scan.nextLine()));
		}
		
		fr = new FileReader("linuxwords_rand.txt");
		scan = new Scanner(fr);
		while(scan.hasNext()) {
			unorder_dictionary.insert(new MyString(scan.nextLine()));
		}
		scan.close();
		System.out.println("Ordered Dictionary # probes: " + ordered_dictionary.numProbes);
		System.out.println("Huckeberry # probes: " + huckleberry.numProbes);
		System.out.println("Unordered Dictrionary # probes: " + unorder_dictionary.numProbes);
		
		float av1, av2, av3;
		av1 = (float)(ordered_dictionary.numProbes * 1.0/45402);
		av2 = (float)(huckleberry.numProbes * 1.0/45402);
		av3 = (float)(unorder_dictionary.numProbes * 1.0/45402);
		
		System.out.println("\nOrdered Dictionary average # probes: " + av1);
		System.out.println("Huckeberry average # probes: " + av2);
		System.out.println("Unordered Dictrionary average # probes: " + av3);
		System.out.println();
		
	}

}
