package HWs;

public class MyQueue<E> {
    //Node class for single linked list queue
    private static class Node<E> {
        //Instance variables for Node
        private E data;
        private Node<E> next;

        //Constructor for Node, to be completed by you
        private Node(E dataItem) { 
        	data = dataItem;
        	next = null;
        }

        //Constructor for Node, to be completed by you
        private Node(E dataItem, Node<E> nodeRef) { 
        	data = dataItem;
        	next = nodeRef;
        }
        
    }

    //Instance variables for queue
    private Node<E> front;
    private Node<E> rear;
    private int size;

    //Constructor for queue, to be completed by you
    public MyQueue () {  
    	rear = new Node<E>(null);
    	front = new Node<E>(null, rear);
    	size = 0;
    }

    //Adds a node to queue, to be completed by you
    public boolean offer(E item) {  
    	if(size == 0) {
    		front = new Node<E>(item, rear);
    	} else if(size == 1) {
    		rear = new Node<E>(item);
    		front.next = (rear);
    	} else {
    		Node<E> temp = new Node<E>(item);
    		rear.next = temp;
    		rear = temp;
    	}
    	
    	size++;
    	return true;
    }

    //Returns the node at front of queue, to be completed by you
    public E peek() {  
    	return front.data;
    }

    //Returns and removes the node at front of queue, to be completed by you
    public E poll() {  
    	E temp = front.data;
    	front = front.next;
    	size--;
    	if(size == 1) {
    		rear = null;
    	}
    	return temp;
    }

    //Returns the data element at a specific index, to be completed by you
    public E get(int index) {  
    	Node<E> temp = front;
    	for(int i = 0; i < index; i++) {
    		temp = temp.next;
    	}
    	
    	return temp.data;
    }

    //Returns the size of the queue, to be completed by you
    public int size() {	
    	return size;
    }
}
