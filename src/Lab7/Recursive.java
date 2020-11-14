package Lab7;

import java.util.ArrayList;
import java.util.Arrays;

class Recursive {
	private static ArrayList<Integer> list = new ArrayList<Integer>();
	private static ArrayList<Integer> reversedList = new ArrayList<Integer>();

	// this one builds a list containing values from 1 to n
	public static ArrayList<Integer> buildList(int n) {
		// write this in terms of a recursive call using a smaller n
		ArrayList<Integer> tempList = null;
		if (n <= 0) // The smallest list we can make
		{
			tempList = new ArrayList<Integer>();
		} else // All other size lists are created here
		{
			tempList = buildList(n - 1);
			tempList.add(n);
		}
		return tempList;

	}

	// this one reverses a list in-place
	public static ArrayList<Integer> reverse(ArrayList<Integer> lst)
	{
		// write this in terms of a recursive call using a smaller lst
		
		if (lst.size() > 1)
		{// Return the list as is – no need to reverse!
			Integer val = lst.remove(0);
			reverse(lst);
			lst.add(val);
			
		} 
		
		return lst;
	}


	// return the sum of all Integers in the ArrayList
	// this should not change the lst argument
	public static int add(ArrayList<Integer> lst) {
		return add(lst, 0);
	}

	// Print out all the contents of the argument
	// this should not change the lst argument
	public static void print(ArrayList<Integer> lst) {
		print(lst, 0);
		return;
	}

	private static int add(ArrayList<Integer> lst, int index) {
		// think of the input is the inclusive sublist of elements from index
		// to lst.size(). make this sublist shorter in the recursive call
		// by incrementing index
		if (index == lst.size()) {
			return 0;
		}
		return lst.get(index) + add(lst, index + 1);
	}

	private static void print(ArrayList<Integer> lst, int index) {
		// write this in the same way as add, above
		if (index == lst.size()) {
			return;
		}
		System.out.print(lst.get(index) + " ");
		print(lst, index + 1);
		return;

	}

	public static void main(String[] args) {
		ArrayList<Integer> lst = Recursive.buildList(10);
		System.out.println("List Forward: " + lst);

		reverse(lst);
		System.out.println("List reversed: " + lst);

		System.out.print("My print: ");
		Recursive.print(lst);
		System.out.println();
		System.out.println("Sum of integers in list: " + Recursive.add(lst));
	}

}