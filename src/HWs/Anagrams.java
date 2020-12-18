package HWs;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Anagrams {

	public static String[] findLargestGroup(String[] initList) {
		int arrayLength = initList.length;
		String[] listSorted = new String[arrayLength];

		for (int i = 0; i < arrayLength; i++) {
			char[] sortedString = initList[i].toCharArray();
			Arrays.sort(sortedString);
			listSorted[i] = new String(sortedString);
		}

		Arrays.sort(listSorted);
		char[] largestGroupRep = findLargestRep(listSorted).toCharArray();
		
		ArrayList<String> finalGroup = new ArrayList<String>();
		for(String x: initList) {
			char[] sortedString = x.toCharArray();
			Arrays.sort(sortedString);
			if(Arrays.equals(sortedString, largestGroupRep)) {
				finalGroup.add(x);
			}
		}

		return finalGroup.toArray(new String[0]);
	}

	public static String findLargestRep(String[] listSorted) {
		int maxCounter = 0;
		int currCounter = 1;
		String largestGroupRep = listSorted[0];
		for (int i = 1; i < listSorted.length; i++) {
			if (listSorted[i].equals(listSorted[i - 1])) {
				currCounter++;
			} else {
				if (currCounter > maxCounter) {
					largestGroupRep = listSorted[i - 1];
					maxCounter = currCounter;
				}
				currCounter = 1;
			}
		}
		
		return largestGroupRep;
	}

	public static void print(String[] list) {
		for (String x : list) {
			System.out.print(x + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) throws FileNotFoundException {
		//String[] temp = { "hello", "whatsup", "nothingmuch", "drapes", "padres", "bum", "parsed",
				//"rasped", "hole", "lehlo", "spared", "spread"};
		//print(findLargestGroup(temp));
		
		
		Scanner scan = new Scanner(new FileReader("huckleberry_short.txt"));
		ArrayList<String> temp2 = new ArrayList<String>();
		while(scan.hasNext()) {
			temp2.add(scan.nextLine());
		}
		print(findLargestGroup(temp2.toArray(new String[0])));
		scan.close();
	}

}
