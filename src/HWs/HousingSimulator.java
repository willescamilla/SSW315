package HWs;

import java.util.ArrayList;
import java.util.Scanner;

public class HousingSimulator {
	// Functions for generating random double
	public static double randomDouble(int from, int to) {
		return Math.random() * to + from;
	}

	// Functions for generating random int
	public static int randomInt(int from, int to) {
		return (int) (Math.random() * to + from);
	}

	// To be completed by you
	public static void runSimulation(int k, int N) {
		MyQueue<Student> students = new MyQueue<Student>();
		ArrayList<Apartment> apts = new ArrayList<Apartment>();

		while (k != 0) {
			students.offer(new Student(randomDouble(0, 1), k));
			k--;
		}
		while (N != 0) {
			apts.add(new Apartment(randomDouble(0, 1), N, 0));
			N--;
		}

		aptSearch(students, apts);

	}

	public static void aptSearch(MyQueue<Student> students, ArrayList<Apartment> apts) {
		if (students.size() == 0) {
			System.out.println("All students are housed");
			return;
		}

		int numFilledThisYear = 0;
		int numVacatedThisYear = 0;

		for (Apartment apt : apts) {
			for (int i = 0; i < students.size() && apt.getYearsLeft() == 0; i++) {
				if (students.peek().getQualityThreshold() < apt.getQuality()) {
					students.poll();
					apt.setYearsLeft(randomInt(1, 3));
					numFilledThisYear++;
				} else {
					students.peek().addDesperation();
					students.offer(students.poll());
				}
			}
			
			// End of school year for this apt
			// Decreasing years left if this apt is non-vacant
			if (apt.getYearsLeft() != 0) {
				apt.setYearsLeft(apt.getYearsLeft() - 1);
				if (apt.getYearsLeft() == 0) {
					numVacatedThisYear++;
				}
			}
		}

		// Output Statistics
		System.out.println("Number of apts newly filled: " + numFilledThisYear);
		System.out.println("Number of apts vacated this year: " + numVacatedThisYear);
		System.out.println("--------------------------------------------------");
		aptSearch(students, apts);
	}

	// Main method for simulation
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter number of students to run simulation with: ");
		int k = sc.nextInt();

		System.out.print("Enter number of apartments to run simulation with: ");
		int N = sc.nextInt();

		System.out.println("--------------------------------------------------");
		runSimulation(k, N);
	}
}
