package com.uca.trees;

public class BST {

	private Node root;

	public void insert(int key) {
		root = insert(root, key);
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
		return n;
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

		public Node(int key) {
			this.key = key;
		}
	}

}
