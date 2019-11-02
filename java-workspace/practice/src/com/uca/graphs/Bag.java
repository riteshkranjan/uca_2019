package com.uca.graphs;
import java.util.Iterator;
public class Bag implements Iterable<Integer> {
	private Node first;
	private int size;
	public void insert(int key) {
		first = insert(first, key);
		size++;
	}
	public int size() {
		return size;
	}
	private Node insert(Node n, int key) {
		if (n == null)
			return new Node(key);
		n.next = insert(n.next, key);
		return n;
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
		return new Iterator<Integer>() {
			private Node temp = first;

			@Override
			public boolean hasNext() {
				return temp != null;
			}

			@Override
			public Integer next() {
				int t = temp.key;
				temp = temp.next;
				return t;
			}
		};
	}
}
