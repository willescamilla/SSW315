package Sandbox;

import java.util.Scanner;
import java.util.Random;

public class CoinPurse {
	// These can be accessed by all future calls within the CoinPurse
	private int numGalleons;
	private int numSickles;
	private int numKnuts;
	private static final int CAPACITY = 256;

	// Opens an empty CoinPurse
	public CoinPurse() {
		numGalleons = 0;
		numSickles = 0;
		numKnuts = 0;
	}

	// Opens a CoinPurse with coins "Galleons" , "Sickles" , "Knuts"
	public CoinPurse(int g, int s, int k) {
		numGalleons = g;
		numSickles = s;
		numKnuts = k;

		// Checks to see if CAPACITY is exceeded or num coins is less than zero.
		if (g + s + k > CAPACITY) {
			throw new IllegalArgumentException("Coin limit exceeded.");
		} else if (g < 0 || s < 0 || k < 0) {
			throw new IllegalArgumentException("Cannot have coin amounts less than zero.");
		}
	}

	// Deposits Galleons into the CoinPurse
	public void depositGalleons(int n) {
		numGalleons += n;
		if (numGalleons + numSickles + numKnuts > CAPACITY) {
			throw new IllegalArgumentException("Coin limit exceeded.");
		} else if (n < 0) {
			throw new IllegalArgumentException("Cannot have coin amounts less than zero.");
		}
	}

	// Deposits Sickles into the CoinPurse
	public void depositSickles(int n) {
		numSickles += n;
		if (numGalleons + numSickles + numKnuts > CAPACITY) {
			throw new IllegalArgumentException("Coin limit exceeded.");
		} else if (n < 0) {
			throw new IllegalArgumentException("Cannot have coin amounts less than zero.");
		}
	}

	// Deposits Knuts into the CoinPurse
	public void depositKnuts(int n) {
		numKnuts += n;
		if (numGalleons + numSickles + numKnuts > CAPACITY) {
			throw new IllegalArgumentException("Coin limit exceeded.");
		} else if (n < 0) {
			throw new IllegalArgumentException("Cannot have coin amounts less than zero.");
		}
	}

	// Withdraws Galleons from the CoinPurse
	public void withdrawGalleons(int n) {
		if (n > numGalleons) {
			throw new IllegalArgumentException("Cannot complete transaction.");
		} else if (n < 0) {
			throw new IllegalArgumentException("Cannot have coin amounts less than zero.");
		}
		numGalleons -= n;
	}

	// Withdraws Sickles from the CoinPurse
	public void withdrawSickles(int n) {
		if (n > numSickles) {
			throw new IllegalArgumentException("Cannot complete transaction.");
		} else if (n < 0) {
			throw new IllegalArgumentException("Cannot have coin amounts less than zero.");
		}
		numSickles -= n;
	}

	// Withdraws Knuts from the CoinPurse
	public void withdrawKnuts(int n) {
		if (n > numKnuts) {
			throw new IllegalArgumentException("Cannot complete transaction.");
		} else if (n < 0) {
			throw new IllegalArgumentException("Cannot have coin amounts less than zero.");
		}
		numKnuts -= n;
	}

	// Checks and returns the number of Coins in CoinPurse
	public int numCoins() {
		return numGalleons + numSickles + numKnuts;
	}

	// Returns total wallet value
	public int totalValue() {
		return (numGalleons * 493) + (numSickles * 29) + (numKnuts);
	}

	// Returns the number of "Galleons" , "Sickles" , "Knuts" to the user
	public String toString() {
		return ("Number of Galleons: " + numGalleons + "\nNumber of Sickles: " + numSickles + "\nNumber of Knuts: "
				+ numKnuts);
	}

	// Returns the exact change for a given value
	public boolean exactChange(int n) {
		int tempGals = numGalleons;
		int tempSickles = numSickles;
		int tempKnuts = numKnuts;

		while (n >= 493 && tempGals > 0) {
			tempGals--;
			n -= 493;
		}

		while (n >= 29 && tempSickles > 0) {
			tempSickles--;
			n -= 29;
		}

		while (n >= 1 && tempKnuts > 0) {
			tempKnuts--;
			n--;
		}

		return n == 0;
	}

	// Returns number of coins of each type within the array
	public int[] withdraw(int n) {
		if (n > totalValue()) {
			throw new IllegalArgumentException("Cannot widthraw value greater than total value of CoinPurse.");
		} else if (exactChange(n) == false) {
			for (int i = 1; i + n < totalValue(); i++) {
				if (exactChange(n + i) == true) {
					return withdraw(n + i);
				}
			}
		}

		int tempGals = numGalleons;
		int tempSickles = numSickles;
		int tempKnuts = numKnuts;

		while (n >= 493 && tempGals > 0) {
			tempGals--;
			n -= 493;
		}

		while (n >= 29 && tempSickles > 0) {
			tempSickles--;
			n -= 29;
		}

		while (n >= 1 && tempKnuts > 0) {
			tempKnuts--;
			n--;
		}

		int[] change = { numGalleons - tempGals, numSickles - tempSickles, numKnuts - tempKnuts };

		withdrawGalleons(numGalleons - tempGals);
		withdrawSickles(numSickles - tempSickles);
		withdrawKnuts(numKnuts - tempKnuts);

		return change;
	}

	public int drawRandCoin() {
		if (numCoins() == 0) {
			throw new IllegalArgumentException("No coins in the purse to randomly draw.");
		}

		int totalNum = numCoins();
		double g = numGalleons / (double) totalNum;
		double s = numSickles / (double) totalNum;
		double k = numKnuts / (double) totalNum;
		double res = Math.random();
		if (res < k) {
			return 0;
		} else if (res > k && res < k + s) {
			return 1;
		} else {
			return 2;
		}

	}

	public int[] drawRandSequence(int n) {
		if (numCoins() == 0) {
			throw new IllegalArgumentException("No coins in the purse to randomly draw.");
		}
		int[] result = new int[n];
		for (int i = 0; i < n; i++) {
			int draw = drawRandCoin();
			result[i] = draw;
		}

		return result;
	}

	public static int compareSequences(int[] coinSeq1, int[] coinSeq2) {
		if (coinSeq1.length != coinSeq2.length) {
			throw new IllegalArgumentException("Coin array lengths are not the same.");
		}

		int length = coinSeq1.length;
		int counter = 0;
		for (int i = 0; i < length; i++) {
			if (coinSeq1[i] > coinSeq2[i]) {
				counter++;
			} else if (coinSeq1[i] < coinSeq2[i]) {
				counter--;
			}
		}

		if (counter < 0) {
			return -1;
		} else if (counter > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public static void main(String[] Args) {
		Scanner scan = new Scanner(System.in);

		CoinPurse myPurse = new CoinPurse(2, 5, 10);
		System.out.println("Initial Value of purse: " + myPurse.totalValue());
		System.out.println(myPurse);

		System.out.print("Enter number to check for exact change: ");
		int n = scan.nextInt();
		System.out.println(myPurse.exactChange(n));

		int[] used = myPurse.withdraw(n);
		System.out.println();
		System.out.println("Coins used: ");
		for (int i = 0; i < 3; i++) {
			System.out.print(used[i] + " ");
		}
		System.out.println("\n");

		System.out.println("Coins left: ");
		System.out.println(myPurse);

	}
}
