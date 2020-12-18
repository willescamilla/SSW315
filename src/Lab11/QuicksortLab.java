package Lab11;

import java.util.Vector;

/**
 * Modify this implementation of Quicksort. Change the private method
 * quicksort() so it is implemented WITHOUT the two vectors, and it their place
 * uses a single array named "extra".
 * 
 */
public class QuicksortLab {
	int[] items = new int[100];

	public void quickSort(int[] arrayToSort) {
		items = arrayToSort;
		quicksort(0, items.length - 1);
		for (int index = 0; index <= items.length - 1; index++) {
			System.out.print(items[index] + " ");
		}
		System.out.println();
	}

	private void quicksort(int from, int to) {
		int pivot = from + (to - from)/2;
		int pivotValue = items[pivot];

		System.out.print("(" + pivot+ "," + to + ") ");
		for (int index = from; index <= to; index++) {
			System.out.print(items[index] + " ");
		}
		System.out.println();

		Vector<Integer> leftPartition = new Vector<Integer>();
		Vector<Integer> rightPartition = new Vector<Integer>();

		for (int i = from; i <= to; i++) {
			if (i != pivot) {
				if (items[i] < pivotValue) {
					leftPartition.addElement(items[i]);
				} else {
					rightPartition.insertElementAt(items[i], 0);
				}
			}
		}

		for (int i = from; i <= to; i++) {
			if ((i - from) < leftPartition.size()) {
				items[i] = ((Integer) leftPartition.elementAt(i - from)).intValue();
			} else if ((i - from) == leftPartition.size()) {
				items[i] = pivotValue;
			} else {
				items[i] = ((Integer) rightPartition.elementAt(i - from - leftPartition.size() - 1)).intValue();
			}
		}

		if (leftPartition.size() > 1) {
			quicksort(from, from + leftPartition.size() - 1);
		}
		if (rightPartition.size() > 1) {
			quicksort(leftPartition.size() + 1 + from, leftPartition.size() + from + rightPartition.size());
		}

	}

	public static void main(String[] Args) {
		QuicksortLab qs = new QuicksortLab();
		int[] temp1 = { 8, 1, 4, 5, 9, 2, 6, 5 };
		qs.quickSort(temp1);

	}

}
