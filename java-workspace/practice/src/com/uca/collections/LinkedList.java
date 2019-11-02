package com.uca.collections;

import java.util.Iterator;

public class LinkedList implements Iterable<Integer>{
	
	public static void main(String[] args) {
		LinkedList l = new LinkedList();
		l.insert(1);
		l.insert(2);
		l.insert(3);
		l.insert(4);
		l.print();
		
		for(int i : l) {
			System.out.println(i);
		}
		Iterator<Integer> ite = l.iterator();
		while(ite.hasNext())
			System.out.println(ite.next());
	}
	
	
	private Node first;

	public void insert(int key) {
		first = insert(first, key);
	}

	private Node insert(Node n, int key) {
		if (n == null)
			return new Node(key);
		n.next = insert(n.next, key);
		return n;
	}

	public void print() {
		Node temp = first;
		while (temp != null) {
			System.out.print(temp.key + "->");
			temp = temp.next;
		}
		System.out.print("null\n");

	}

	private class Node {
		private int key;
		private Node next;

		public Node(int key) {
			this.key = key;
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return new MyIterator();
	}
	
	private class MyIterator implements Iterator<Integer>{
        private Node temp = first;
		@Override
		public boolean hasNext() {
			return temp!=null;
		}

		@Override
		public Integer next() {
			int t = temp.key;
			temp = temp.next;
			return t;
		}
		
	}

}
