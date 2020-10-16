package Lab5;

import Lab4.Node;

public class MyDLLImpl<E> implements MyDoubleLinkedList {
	private MyDoubleNode head;
	private MyDoubleNode tail;
	private int size;

	public MyDLLImpl() {
		// TODO Auto-generated constructor stub
		head = new MyDoubleNode(null, tail, null);
		tail = new MyDoubleNode(null, null, head);
		size = 0;
	}

	@Override
	public void insert(Object x) {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			head.data = x;
		} else if (tail.data == null) {
			tail.data = x;
		} else {
			MyDoubleNode temp1 = tail;
			MyDoubleNode temp2 = new MyDoubleNode(x, null, temp1);
			temp1.next = temp2;
			temp1.prev.next = temp1;
			tail = temp2;
		}
		size++;
	}

	public void insert(Object x, MyDoubleNode a) {
		// TODO Auto-generated method stub
		if(!lookup(a.data)) {
			System.out.println("The target node does not exist");
			return;
		}
		MyDoubleNode temp = new MyDoubleNode(x, a.next, a);
		a.next.prev = temp;
		a.next = temp;
		size++;
	}

	@Override
	public void delete(Object x) {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			System.out.println("Error: List is empty");
			return;
		}

		MyDoubleNode temp = head;
		while (temp.data != x) {
			if (temp.next == null) {
				return;
			}
			temp = temp.next;
		}

		if (temp == head) {
			temp.data = null;
			if (temp.next != null && temp.next != tail) {
				head = temp.next;
			}
		} else if (temp == tail) {
			temp.data = null;
			if (temp.prev != null && temp.prev != head) {
				tail = temp.prev;
			}
		} else {
			temp.prev.next = temp.next;
			temp.next.prev = temp.prev;
		}

		size--;
	}

	@Override
	public boolean lookup(Object x) {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			return false;
		}
		MyDoubleNode temp = head;
		while (temp.data != x) {
			if (temp.next == null) {
				return false;
			}
			temp = temp.next;
		}
		return true;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	public int getSize() {
		return size;
	}

	@Override
	public void printList() {
		// TODO Auto-generated method stub
		MyDoubleNode temp = head;
		StringBuilder sb = new StringBuilder();
		sb.append("{ ");
		while (temp != tail) {
			sb.append(temp.data);
			sb.append(", ");
			temp = temp.next;
		}
		sb.append(temp.data);
		sb.append(" }");
		System.out.println(sb);
	}

	@Override
	public void printListRev() {
		// TODO Auto-generated method stub
		MyDoubleNode temp = tail;
		StringBuilder sb = new StringBuilder();
		sb.append("{ ");
		while (temp != head) {
			sb.append(temp.data);
			sb.append(", ");
			temp = temp.prev;
		}
		sb.append(temp.data);
		sb.append(" }");
		System.out.println(sb);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyDLLImpl<Integer> dll = new MyDLLImpl<Integer>();
		dll.insert(10);
		dll.insert(34);
		dll.insert(56);
		dll.insert(364);
		dll.printList();
		dll.delete(56);
		dll.delete(34);
		dll.printListRev();

	}

}
