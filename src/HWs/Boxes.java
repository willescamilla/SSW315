package HWs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Boxes {
	PriorityQueue<Float> boxes;
	
	public Boxes() {
		boxes = new PriorityQueue<Float>();
	}
	
	public void insert(float item) {
		Float[] temp = boxes.toArray(new Float[boxes.size()]);
		boolean wasInserted = false;
		
		for(int i = boxes.size()-1; i >= 0; i--) {
			if(temp[i]+item <= 1.0) {
				temp[i] += item;
				wasInserted = true;
				break;
			}
		}
		
		if(!wasInserted) {
			boxes.add(item);
		} else {
			boxes.clear();
			for(int i = 0; i < temp.length; i++) {
				boxes.add(temp[i]);
			}
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		Float[] temp = boxes.toArray(new Float[0]);
		sb.append("Printing boxes:\n[");
		for(int i = 0; i < boxes.size()-1; i++) {
			sb.append(String.format ("%,.1f", temp[i]) + ", ");
		}
		sb.append(String.format ("%,.1f", temp[boxes.size()-1]) + "]");
		
		return sb.toString();
	}

	public static void main(String[] args) {
		Boxes bum = new Boxes();
		ArrayList<Float> temp = new ArrayList<Float>();
		temp.add((float).4);
		temp.add((float).4);
		temp.add((float).5);
		temp.add((float).9);
		temp.add((float).2);
		temp.add((float).3);
		temp.add((float).1);
		temp.add((float).7);
		System.out.println("Adding items as is:");
		for(int i = 0; i < temp.size(); i++) {
			bum.insert(temp.get(i));
		}
		System.out.println(bum);
		System.out.println("--------------------------");
		
		bum = new Boxes();
		temp = new ArrayList<Float>();
		temp.add((float).4);
		temp.add((float).4);
		temp.add((float).5);
		temp.add((float).9);
		temp.add((float).2);
		temp.add((float).3);
		temp.add((float).1);
		temp.add((float).7);
		Collections.sort(temp);
		Collections.reverse(temp);
		System.out.println("Adding items AFTER sorting high-low:");
		for(int i = 0; i < temp.size(); i++) {
			bum.insert(temp.get(i));
		}
		System.out.println(bum);
	}

}
