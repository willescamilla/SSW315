package Lab10;

//QuadraticProbingHashTable abstract class
//
// CONSTRUCTION: with an approximate initial size or a default.
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// Hashable find( x )     --> Return item that matches x
// void makeEmpty( )      --> Remove all items
// int hash( String str, int tableSize )
//                        --> Static method to hash strings

/**
 * Probing table implementation of hash tables. Note that all "matching" is
 * based on the equals method.
 * 
 * @author Mark Allen Weiss
 */
public class QuadraticProbingHashTable {
	/**
	 * Construct the hash table.
	 */
	public QuadraticProbingHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	/**
	 * Construct the hash table.
	 * 
	 * @param size the approximate initial size.
	 */
	public QuadraticProbingHashTable(int size) {
		numProbes = 0;
		allocateArray(size);
		makeEmpty();
	}

	/**
	 * Insert into the hash table. If the item is already present, do nothing.
	 * 
	 * @param x the item to insert.
	 */
	public void insert(Hashable x) {
		// Insert x as active
		// If the item is already present, do nothing
		int currentPos = findPos(x);
		if (isActive(currentPos)) {
			numProbes++;
			return;
		}

		array[currentPos] = new HashEntry(x, true);
		numProbes++;

		// Rehash; see Section 5.5
		// Need to reallocate more space for our hash table
		if (++currentSize > array.length / 2) {
			rehash();
		}
	}

	/**
	 * Expand the hash table.
	 */
	private void rehash() {
		HashEntry[] oldArray = array;

		// Create a new double-sized, empty table
		allocateArray(nextPrime(2 * oldArray.length));
		currentSize = 0;

		// Copy table over
		int i = 0;
		// Examine every element in oldArray
		while (i < oldArray.length) {
			// If an element exists in our oldArray, insert it
			// into our new array
			if (oldArray[i] != null && oldArray[i].isActive) {
				insert(oldArray[i].element);
			}
			i++;
		}

		return;
	}

	/**
	 * Method that performs quadratic probing resolution.
	 * 
	 * @param x the item to search for.
	 * @return the position where the search terminates.
	 */
	private int findPos(Hashable x) {
		int collisionNum = 0;
		int currentPos = x.hash(array.length);

		// If an element exists in the spot where we are about
		// to enter a new element, and that element does not equal
		// our new element. We have a collision
		while (array[currentPos] != null && !array[currentPos].element.equals(x)) {
			numProbes++;
			currentPos += 2 * ++collisionNum - 1;
			
			// Compute ith probe
			// If this new currentPos is too large, set it back to
			// somewhere near the beginning
			if (currentPos >= array.length) {
				// Implement the mod
				currentPos -= array.length;
			}
		}

		return currentPos;
	}

	/**
	 * Remove from the hash table.
	 * 
	 * @param x the item to remove.
	 */
	public void remove(Hashable x) {
		int currentPos = findPos(x);
		// If the element at currentPos is active
		// Make it not so
		if (isActive(currentPos)) {
			array[currentPos].isActive = false;
		}
	}

	/**
	 * Find an item in the hash table.
	 * 
	 * @param x the item to search for.
	 * @return the matching item.
	 */
	public Hashable find(Hashable x) {
		int currentPos = findPos(x);
		// If there is an active element at currentPos
		// return that element
		if(isActive(currentPos)) {
			return array[currentPos].element;
		}
		return null;
	}

	/**
	 * Return true if currentPos exists and is active.
	 * 
	 * @param currentPos the result of a call to findPos.
	 * @return true if currentPos is active.
	 */
	private boolean isActive(int currentPos) {
		// Checks if an element exists at currentPos
		// and if it is active
		return array[currentPos] != null && array[currentPos].isActive;
	}

	/**
	 * Make the hash table logically empty.
	 */
	public void makeEmpty() {
		currentSize = 0;
		// Go through every element in array and set it to null
		for (int i = 0; i < array.length; i++) {
			array[i] = null;
		}
	}

	/**
	 * A hash routine for String objects.
	 * 
	 * @param key       the String to hash.
	 * @param tableSize the size of the hash table.
	 * @return the hash value.
	 */
	public static int hash(String key, int tableSize) {
		int hashVal = 0;

		// Examine every letter in the key
		for (int i = 0; i < key.length(); i++) {
			hashVal = 37 * hashVal + key.charAt(i);
		}

		hashVal %= tableSize;
		// If the remainder of hashVal/tableSize is less than 0,
		// Increase the hashVal
		if (hashVal < 0) {
			hashVal += tableSize;
		}

		return hashVal;
	}

	private static final int DEFAULT_TABLE_SIZE = 11;

	/** The array of elements. */
	private HashEntry[] array; // The array of elements
	private int currentSize; // The number of occupied cells
	public int numProbes;

	/**
	 * Internal method to allocate array.
	 * 
	 * @param arraySize the size of the array.
	 */
	private void allocateArray(int arraySize) {
		array = new HashEntry[arraySize];
	}

	/**
	 * Internal method to find a prime number at least as large as n.
	 * 
	 * @param n the starting number (must be positive).
	 * @return a prime number larger than or equal to n.
	 */
	private static int nextPrime(int n) {
		// If n is even
		if (n % 2 == 0) {
			n++;
		}

		// Keep adding 2 to n until n is prime
		for (; !isPrime(n); n += 2) {
			;
		}

		return n;
	}

	/**
	 * Internal method to test if a number is prime. Not an efficient algorithm.
	 * 
	 * @param n the number to test.
	 * @return the result of the test.
	 */
	private static boolean isPrime(int n) {
		// Self explanitory
		// If n is 2 or 3 it is prime so return true
		if (n == 2 || n == 3) {
			return true;
		}

		// If n is 1 or its a multiple of 2
		// return false
		if (n == 1 || n % 2 == 0) {
			return false;
		}

		// From 3 to sqrt(n), increment 3 by 2 each time
		// Similar to Sieve of Eratosthenes
		for (int i = 3; i * i <= n; i += 2) {
			// If n is divisble by any of these prime numbers
			// Its not prime
			if (n % i == 0) {
				return false;
			}
		}

		return true;
	}

	public HashEntry[] getTable() {
		HashEntry[] clone = array.clone();
		return clone;
	}

	// Simple main
	public static void main(String[] args) {
		QuadraticProbingHashTable H = new QuadraticProbingHashTable(4000);

		final int NUMS = 4000;
		final int GAP = 37;

		System.out.println("Checking... (no more output means success)");

		for (int i = GAP; i != 0; i = (i + GAP) % NUMS)
			H.insert(new MyInteger(i));
		for (int i = 1; i < NUMS; i += 2)
			H.remove(new MyInteger(i));

		for (int i = 2; i < NUMS; i += 2)
			if (((MyInteger) (H.find(new MyInteger(i)))).intValue() != i)
				System.out.println("Find fails " + i);

		for (int i = 1; i < NUMS; i += 2) {
			if (H.find(new MyInteger(i)) != null)
				System.out.println("OOPS!!! " + i);
		}

	}

}
