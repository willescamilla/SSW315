package Lab3;

import java.util.Scanner;

/*This program prompts the user to enter several numbers,
stores the numbers into an array,
then prints the numbers in forwards and backwards order.

Expected example output:

How many numbers will you enter? 4
Type a number: 12
Type a number: 8
Type a number: -2
Type a number: 39

Your numbers in forward order:
12
8
-2
39

Your numbers in backward order:
39
-2
8
12
*/

public class PromptNumbers {
	
	public static void printForward(int[] list) {
		for (int i = 0; i < list.length; i++) {
			// your code goes here; print the numbers in forward order
			if(i != list.length -1) {
				System.out.print(list[i] + ", ");
			}
			else {
				System.out.println(list[i]);
			}
		}
	}
	
	public static void printBackwards(int[] list) {
		for(int i = list.length -1; i >= 0; i--) {
			if(i != 0) {
				System.out.print(list[i] + ", ");
			}
			else {
				System.out.println(list[i]);
			}
		}
	}
	
	public static void swapAll(int[] list1, int[] list2) {
		int[] temp = (int[]) list1.clone();
		System.arraycopy(list2, 0, list1, 0, list2.length);
		System.arraycopy(temp, 0, list2, 0, temp.length);
	}
	
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		System.out.print("How many numbers will you enter? ");
		int count = console.nextInt();
		
		int[] arr = new int[count];
		for (int i = 0; i < count; i++) {
			// your code goes here; store the numbers
			System.out.print("Type a number: ");
			arr[i] = console.nextInt();
		}
		
		System.out.println();
		System.out.println("Your numbers in forward order:");
		printForward(arr);

		System.out.println();
		System.out.println("Your numbers in backward order:");
		printBackwards(arr);
		
		int[] a1 = {11, 42, -5, 27, 0, 89};
		int[] a2 = {10, 20, 30, 40, 50, 60};
		
		System.out.println();
		System.out.println("List1 before swapping:");
		printForward(a1);
		System.out.println("List2 before swapping:");
		printForward(a2);
		
		swapAll(a1, a2);
		
		System.out.println();
		System.out.println("List1 after swapping:");
		printForward(a1);
		System.out.println("List2 after swapping:");
		printForward(a2);
		
		console.close();
	}
}
