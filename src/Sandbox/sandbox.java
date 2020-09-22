package Sandbox;
import java.util.Scanner;

public class sandbox {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		int b = 5;
		int c = 8;
		int a = 0;
		System.out.println("a: "+a+" b: " + b + " c: " + c);
		a = b++ + c++;
		System.out.println("a: "+a+" b: " + b + "c: " + c);
		a = b++ + ++c;
		System.out.println("a: "+a+" b: " + b + "c: " + c);
		a = ++b + c++;
		System.out.println("a: "+a+" b: " + b + "c: " + c);
		a = ++b + ++c;
		System.out.println("a: "+a+" b: " + b + "c: " + c);
		
		scan.close();
		
	}

}
