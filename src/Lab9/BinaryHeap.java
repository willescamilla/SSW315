package Lab9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * This class implements a binary heap data structure by extending the
 * ArrayList class.
 * It also implements the java.util.Queue interface so that it can be
 * used with the framework problem solver.
 * @author tcolburn
 * @param <E> the type of element stored on this binary heap
 */
public class BinaryHeap<E> extends ArrayList<E> implements Queue<E> {

	/**
	 * Creates an empty binary heap with a given capacity and comparator.
	 * @param capacity The initial size of the underlying ArrayList object.
	 * @param comp A comparator object for comparing keys of binary heap elements.
	 */
	public BinaryHeap(int capacity, Comparator<E> comp) {
		super(capacity+1);
		init();
		this.comp = comp;
	}

	/**
	 * Getter for the comparator for this binary heap.
	 * @return the comparator for this binary heap
	 */
	public Comparator<E> getComp() {
		return comp;
	}

	/**
	 * Initializes the underlying ArrayList object for use as a binary heap.
	 * A null object is added to location 0, which is not used by the heap.
	 */
	private void init() {
		add(0, null);
	}

	/**
	 * Clears this binary heap by clearing and initializing the underlying
	 * ArrayList object.
	 */
	@Override
	public void clear() {
		super.clear();
		init();
	}

	/**
	 * Returns the current size of this binary heap.  Since the first location 
	 * (index 0) of the underlying ArrayList object is not used, the size of the 
	 * binary heap is one less than the size of the ArrayList object.
	 * @return The binary heap's current size. 
	 */
	@Override
	public int size() {
		return super.size()-1;
	}

	/**
	 * Returns true if this binary heap is empty, that is, its size is zero.
	 * @return Whether this binary heap is empty.
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Adds a new element to this binary heap.  At the end of the add,
	 * the heap has one more element and the heap property is maintained.
	 * @param element The element to add
	 * @return true.  In accordance with the Collection interface, returns
	 * true since duplicate elements are allowed.
	 */
	@Override
	public boolean add(E element) {
		// add element to the ArrayList, by default, add to the end of list;
		super.add(element); 


		if (this.size() == 1) 
			return true;	//Only contains 1 root node, no need to compare
		else {
			//create a hole at the end of the list; what is the index for the end of list??
			int hole = this.size();

			//Get parent index; what is the parent idex?
			int parent = hole/2;

			//Get the list comparator to compare two objects. We will cover this later. 
			Comparator<E> comp = this.getComp();

			//Iteratively bubble up the hole 
			for (; (comp.compare(element,this.get(parent)) < 0); hole=parent)
			{	
				//if the element is less than its parent, you can 
				//copy the parent value to position indexed by the hole;
				set(hole, this.get(parent));

				//Update parent index in order to move one level up
				parent = parent/2;

				//Check if it reaches the root node, if yes then break out the loop
				if (parent == 0) 
					break;
			}
			//
			set(hole, element);
		}
		return true;
	}

	/**
	 * Removes an element from the root of this binary heap.  After removal,
	 * the heap has one less element and the heap property is restored.
	 * This method does not override any method in the ArrayList class 
	 * (note absence of an index parameter).
	 * However, it implements a method in the Queue interface.
	 * @return The removed element
	 */
	@Override
	public E remove() {

		/* You must complete this part. */
		// if empty heap, throw exception
		if(this.size()==0) 
    		throw new NoSuchElementException();
    		
    	// if only contains the root node, remove it by calling ArrayList's default remove();
		if(this.size()==1) 
    		return super.remove(this.size());
    	
    	// Get minimum element in the list, at index of 1, to be removed;
    	E minElement = this.get(1);
    	
    	// Remove the last element in the list, which is used to fill the hole at root;
    	E lastElement = super.remove(this.size());
    	
    	// Copy last element to the root (i.e. the hole)
    	this.set(1, lastElement);
        
    	// Fix heap order property
    	bubbleDown(1);
    	
    	return minElement;
   
	}

    private void bubbleDown(int hole){
    	int child;
    	E element = this.get(1);
    	Comparator<E> comp = this.getComp();
    	
    	//Implement the iterative bubble down to swap element with the large child
    	// To compare the values of children, use the following syntax:
    	// comp.compare(this.get(child+1), this.get(child))<0 
    	// which means that the right child is smaller
    	for(; hole*2 <= this.size(); hole = child) {
    		child = hole*2;
    		if(child != this.size() && comp.compare(this.get(child+1), this.get(child)) < 0) {
    			child++;
    		}
    		if(comp.compare(this.get(child), element) < 0) {
    			this.set(hole, this.get(child));
    		}
    		else {
    			break;
    		}
    	}
    	
    	// After the for loop above, you find the right position, i.e. the hole index
    	// where you should save the element.
    	this.set(hole, element);
    }

	
	/**
	 * A Comparator object used to compare binary heap elements during its
	 * add and remove operations.
	 */
	private final Comparator<E> comp;

	/*
	    The following are required to complete the implementation of the Queue<E> 
	    interface. They are not used in the test.
	 */

	@Override
	public boolean offer(E e) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public E poll() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public E element() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public E peek() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder("");
		s.append('[');
		for(int i = 1; i < this.size(); i++) {
			s.append(this.get(i) + ", ");
		}
		s.append(this.get(this.size()) + "]");
		return s.toString();
	}
	
	public static void main(String[] args) {
		Comparator<Integer> comp = (i1, i2) -> Integer.compare(i1, i2); 

        BinaryHeap<Integer> bh = new BinaryHeap<Integer>(8, comp);
        bh.add(7);
        System.out.println(bh);
        bh.add(8);
        System.out.println(bh);
        bh.add(1);
        System.out.println(bh);
        bh.add(6);
        System.out.println(bh);
        bh.add(2);
        System.out.println(bh);
        bh.add(3);
        System.out.println(bh);
        bh.add(5);
        System.out.println(bh);
        bh.add(4);
        System.out.println(bh);
	}
}

