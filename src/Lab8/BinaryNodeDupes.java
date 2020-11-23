package Lab8;
//Basic node stored in unbalanced binary search trees

import java.util.ArrayList;

//Note that this class is not accessible outside
//of this package.

class BinaryNodeDupes<AnyType> {
	// Constructor
	BinaryNodeDupes(AnyType theElement) {
		element = theElement;
		left = right = null;
		duplicates = new ArrayList<BinaryNodeDupes<AnyType>>();
	}

	// Data; accessible by other package routines
	AnyType element; // The data in the node
	BinaryNodeDupes<AnyType> left; // Left child
	BinaryNodeDupes<AnyType> right; // Right child
	ArrayList<BinaryNodeDupes<AnyType>> duplicates;
}