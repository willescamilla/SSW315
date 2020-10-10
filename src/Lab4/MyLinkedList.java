package Lab4;
public class MyLinkedList { //Start of class MyLinkedList 
	private Node first;
	private Node last;
	
	//LinkList constructor 
	public MyLinkedList() 
	{ 
		first = null; 
		last = null;
		
	}
	
	//Returns true if the linked list is empty 
	public boolean isEmpty() 
	{ 
		return first == null; 
		
	}

	//Inserts a new node at the first of the linked list
	public void addFirst(int d1, double d2) 
	{ 
		Node node = new Node(d1, d2); 
		node.next = first; 
		first = node;
		
		Node temp = first;
		while(temp.next != null) {
			temp = temp.next;
		}
		last = temp;
	}
	
	//Deletes the node at the first of the linked list
	public Node deleteFirst() 
	{ 
		Node temp = first; 
		first = first.next; 
		return temp;
	}
	
	public void addLast(int d1, double d2) {
		last.next = new Node(d1, d2);
		last = last.next;
	}
	
	public Node deleteLast() {
		Node temp = first;
		while(temp.next != last) {
			temp = temp.next;
		}
		
		Node nodeRemoved = last;
		temp.next = null;
		last = temp;
		return nodeRemoved;
	}
	
	public int size() {
		if(first == null) {
			return 0;
		}
		
		int count = 1;
		Node temp = first;
		while(temp.next != null) {
			count++;
			temp = temp.next;
		}
		
		return count;
	}
	
	public void reverse() {
		Node reversed = null;
		Node current = first;
		
		while(current != null) {
			Node next = current.next;
			current.next = reversed;
			reversed = current;
			current = next;
		}
		last = first;
		first = reversed;
	
	}
	
	//Prints the linked list data
	public void printList() 
	{ 
		Node currentNode = first; 
		System.out.print("List: "); 
		while(currentNode!= null) 
		{ 
			currentNode. printNode(); 
			currentNode = currentNode.next; 
		} 
		System.out.println(""); 
		
	} 	
} //End of class LinkList

//The following code is to test your linked list.
/*
 * public class MyLinkedListTest { //Start of class MyLinkedListTest public
 * static void main(String[] args) { MyLinkedList list = new MyLinkedList();
 * list.addFirst(1, 1.01); list.printList(); list.addFirst(2, 2.02);
 * list.printList(); list.addFirst(3, 3.03); list.printList(); list.addFirst(4,
 * 4.04); list.printList(); list.addFirst(5, 5.05); list.printList();
 * 
 * while(!list.isEmpty()) { Node deletedLink = list.deleteFirst();
 * System.out.print("deleted: "); deletedLink.printNode();
 * System.out.println(""); } list.printList(); } }
 */ //End of class MyLinkedListTest
