package Lab9;

import java.util.ArrayList;
import java.util.Comparator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tcolburn
 */
public class BinaryHeapTest {

    /**
     * A binary heap of Integer objects
     */
    private final BinaryHeap<Integer> bh;

    /**
     * Some Integer nodes for testing
     */
    private final Integer n1, n2, n3, n4, n5, n6, n7, n8, n9, n10;

    public BinaryHeapTest() {
        
        /**
         * A comparator for Integers as a lambda expression
         */
        Comparator<Integer> comp = (i1, i2) -> Integer.compare(i1, i2); 

        bh = new BinaryHeap<>(10, comp);

        n1 = 1;
        n2 = 2;
        n3 = 3;
        n4 = 4;
        n5 = 5;
        n6 = 6;
        n7 = 7;
        n8 = 8;
        n9 = 9;
        n10 = 10;
    }


    @Test
    public void testAdd() {

        buildHeap();

        assertTrue(leftChild(n1) == n2);
        assertTrue(rightChild(n1) == n5);
        assertTrue(leftChild(n2) == n4);
        assertTrue(rightChild(n2) == n3);
        assertTrue(leftChild(n5) == n9);
        assertTrue(rightChild(n5) == n6);
        assertTrue(leftChild(n4) == n10);
        assertTrue(rightChild(n4) == n7);
        assertTrue(leftChild(n3) == n8);
        assertTrue(rightChild(n3) == null);
        assertTrue(leftChild(n9) == null);
        assertTrue(rightChild(n9) == null);
        assertTrue(leftChild(n6) == null);
        assertTrue(rightChild(n6) == null);

        assertTrue(parent(n10) == n4);
        assertTrue(parent(n7) == n4);
        assertTrue(parent(n8) == n3);
        assertTrue(parent(n4) == n2);
        assertTrue(parent(n3) == n2);
        assertTrue(parent(n9) == n5);
        assertTrue(parent(n6) == n5);
        assertTrue(parent(n2) == n1);
        assertTrue(parent(n5) == n1);
        assertTrue(parent(n1) == null);
    }

    /**
     * An auxiliary method that builds a heap that should look like:
     *
     *              1
     *            /   \
     *          2       5
     *         /  \    / \
     *       4     3  9   6
     *      / \   /
     *    10   7 8
     */
    private void buildHeap() {

        bh.clear();

        assertTrue(bh.isEmpty());

        bh.add(n10);  assertTrue(isMinBHeap(bh));
        bh.add(n9);   assertTrue(isMinBHeap(bh));
        bh.add(n8);   assertTrue(isMinBHeap(bh));
        bh.add(n7);   assertTrue(isMinBHeap(bh));
        bh.add(n6);   assertTrue(isMinBHeap(bh));
        bh.add(n5);   assertTrue(isMinBHeap(bh));
        bh.add(n4);   assertTrue(isMinBHeap(bh));
        bh.add(n3);   assertTrue(isMinBHeap(bh));
        bh.add(n2);   assertTrue(isMinBHeap(bh));
        bh.add(n1);   assertTrue(isMinBHeap(bh));

        assertTrue(bh.size() == 10);
    }

    @Test
    public void testRemove() {

        buildHeap();
        
        assertTrue(bh.remove().equals(n1));  assertTrue(isMinBHeap(bh));
        assertTrue(bh.remove().equals(n2));  assertTrue(isMinBHeap(bh));
        assertTrue(bh.remove().equals(n3));  assertTrue(isMinBHeap(bh));
        assertTrue(bh.remove().equals(n4));  assertTrue(isMinBHeap(bh));
        assertTrue(bh.remove().equals(n5));  assertTrue(isMinBHeap(bh));
        assertTrue(bh.remove().equals(n6));  assertTrue(isMinBHeap(bh));
        assertTrue(bh.remove().equals(n7));  assertTrue(isMinBHeap(bh));
        assertTrue(bh.remove().equals(n8));  assertTrue(isMinBHeap(bh));
        assertTrue(bh.remove().equals(n9));  assertTrue(isMinBHeap(bh));
        assertTrue(bh.remove().equals(n10));  assertTrue(isMinBHeap(bh));

        assertTrue(bh.isEmpty());

    }

    /**
     * Returns the left child of a given element in this binary heap, or null if
     * the element has no left child.
     * This method performs a search of the underlying ArrayList object
     * (using the indexOf method) and should be used for testing purposes only.
     * It is not used to implement binary heap adds or removes.
     * @param element A binary heap element
     * @return The element's left child or null
     */
    private Object leftChild(Object element) {
        int index = bh.indexOf(element)*2;
        if ( outOfRange(index) )
            return null;
        else
            return bh.get(index);
    }

    /**
     * Returns the right child of a given element in this binary heap, or null if
     * the element has no right child.
     * This method performs a search of the underlying ArrayList object
     * (using the indexOf method) and should be used for testing purposes only.
     * It is not used to implement binary heap adds or removes.
     * @param element A binary heap element
     * @return The element's right child or null
     */
    private Object rightChild(Object element) {
        int index = bh.indexOf(element)*2+1;
        if ( outOfRange(index) )
            return null;
        else
            return bh.get(index);
    }

    /**
     * Returns the parent of a given element in this binary heap, or null if
     * the element is the root.
     * This method performs a search of the underlying ArrayList object
     * (using the indexOf method) and should be used for testing purposes only.
     * It is not used to implement binary heap adds or removes.
     * @param element A binary heap element
     * @return The element's parent or null
     */
    private Object parent(Object element) {
        int index = bh.indexOf(element)/2;
        if ( outOfRange(index) )
            return null;
        else 
            return bh.get(index);
    }

    /**
     * Returns whether a given index is out of range for this binary heap.
     * Since the first element of the underlying ArrayList object is not used,
     * and index is out of range if it is less than 1 or greater than the
     * size of this binary heap.
     * @param index An index into this binary heap
     * @return Whether the index is out of range
     */
    private boolean outOfRange(int index) {
        return index < 1 || index > bh.size();
    }
    
    /**
     * Calculates the depth of a node at a given index in any binary heap.
     * This will be used to test whether an array has the heap property.
     * @param index the index of a binary heap node
     * @return the depth of a node at a given index in any binary heap
     */
    private static int depth(int index) {
        return (int) Math.floor(Math.log(index)/Math.log(2));
    }
    
    /**
     * Determines whether the path from a given leaf in a binary heap is valid.
     * It is valid if the key of the child is always greater than or equal to
     * the key of the parent, and the length of the path from the leaf to the 
     * root is equal to the calculated depth of the child's index in a nearly 
     * full tree.
     * @param index the index of the leaf in the binary heap
     * @param heap the binary heap
     * @return 
     */
    private boolean isMinBHeapPath(int index, BinaryHeap<Integer> heap) {
        int depth = depth(index);
        int count = 0;
        while (index > 1) {
            int child = heap.get(index);
            int parent = heap.get(index/2);
            if (heap.getComp().compare(child, parent) < 0) {
                return false; // heap property violated
            }
            index = index / 2;
            count++;
        }
        return count == depth; // the length of path from leaf to root must be the depth
    }
    
    /**
     * Determines whether a given binary heap actually is a binary heap.
     * Finds the index of each leaf and checks whether the path from it
     * to the root is valid.
     * @param heap the binary heap being checked
     * @return true if all paths from leaves to root are valid; false otherwise
     */
    private boolean isMinBHeap(BinaryHeap<Integer> heap) {
        int firstLeafIndex = heap.size()/2 + 1;
        for (int index = firstLeafIndex; index <= heap.size(); index++ ) {
            if (!isMinBHeapPath(index, heap)) {
                return false;
            }
        }
        return true;
    }

}