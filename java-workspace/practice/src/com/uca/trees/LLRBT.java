package com.uca.trees;

public class LLRBT {

	private Node root;
	private final boolean RED = true;
	private final boolean BLACK = false;

	public void insert(int key) {
		root = insert(root, key);
		root.color = BLACK;
	}

	private Node insert(Node n, int key) {
		if (n == null)
			return new Node(key);
		if (n.key > key)
			n.left = insert(n.left, key);
		else if (n.key < key)
			n.right = insert(n.right, key);
		else {
			// do nothing -- modify value
		}
		if (getColor(n.left) != RED && getColor(n.right) == RED)
			n = leftRotate(n);
		if (getColor(n.left) == RED && getColor(n.left.left) == RED)
			n = rightRotate(n);
		if (getColor(n.left) == RED && getColor(n.right) == RED)
			n = flipColor(n);
		return n;
	}

	private boolean getColor(Node n) {
		return n == null ? BLACK : n.color;
	}

	private Node flipColor(Node n) {
		n.color = RED;
		n.left.color = BLACK;
		n.right.color = BLACK;
		return n;
	}

	private Node rightRotate(Node n) {
		Node t = n.left;
		n.left = t.right;
		t.right = n;
		t.color = n.color;
		n.color = RED;
		return t;
	}

	private Node leftRotate(Node n) {
		Node t = n.right;
		n.right = t.left;
		t.left = n;
		t.color = n.color;
		n.color = RED;
		return t;
	}

	public int height() {
		return root == null ? 0 : height(root);
	}

	private int height(Node n) {
		if (n == null)
			return 0;
		return 1 + Math.max(height(n.left), height(n.right));
	}

	private class Node {
		private int key;
		private Node left;
		private Node right;
		private boolean color;

		public Node(int key) {
			this.key = key;
			this.color = RED;
		}
	}

}
