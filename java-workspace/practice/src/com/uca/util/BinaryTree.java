package com.uca.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.TreeMap;

public class BinaryTree<K> {
	private Node root;

	public void insert(K key) {
		root = insert(root, key);
	}

	private Node insert(Node n, K key) {
		if (n == null)
			return new Node(key);
		if (sizeOf(n.left) == sizeOf(n.right)) {
			n.left = insert(n.left, key);
		} else {
			n.right = insert(n.right, key);
		}
		n.N = 1 + sizeOf(n.left) + sizeOf(n.right);
		return n;
	}

	public Map<Integer, List<K>> fetchNodes() {
		Map<Integer, List<K>> m = new TreeMap<>();
		Queue<NodeWithLevel> q = new LinkedList<>();
		q.add(new NodeWithLevel(root, 0));
		while (!q.isEmpty()) {
			NodeWithLevel n = q.poll();
			if (m.get(n.level) == null) {
				List<K> l = new ArrayList<>();
				l.add(n.n.key);
				m.put(n.level, l);
			} else {
				m.get(n.level).add(n.n.key);
			}
			if (n.n.left != null) {
				q.add(new NodeWithLevel(n.n.left, n.level + 1));
			}
			if (n.n.right != null) {
				q.add(new NodeWithLevel(n.n.right, n.level + 1));
			}
		}
		return m;
	}

	private class NodeWithLevel {
		private Node n;
		private int level;

		public NodeWithLevel(Node n, int level) {
			this.n = n;
			this.level = level;
		}
	}

	private int sizeOf(Node n) {
		return n == null ? 0 : n.N;
	}

	private class Node {
		private K key;
		private Node left;
		private Node right;
		private int N;

		public Node(K key) {
			this.key = key;
			this.N = 1;
		}

		@Override
		public String toString() {
			return "Node " + key;
		}

	}

	public static void main(String[] args) {
		BinaryTree<Integer> b = new BinaryTree<>();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter no of noded to be added ");
		int n = sc.nextInt();
		sc.close();
		for (int i = 0; i < n; i++)
			b.insert(i);

		Map<Integer, List<Integer>> m = b.fetchNodes();
		Iterator<Integer> iterator = m.keySet().iterator();
		int expectedNoOfNodes = 1;
		while (iterator.hasNext()) {
			int k = iterator.next();
			System.out.println("level" + k + " : " + m.get(k).toString() + " => total " + m.get(k).size());
			if (iterator.hasNext() && m.get(k).size() != expectedNoOfNodes)
				throw new AssertionError("expected all leaf nodes to have " + expectedNoOfNodes + "(2^^n) elements");
			expectedNoOfNodes *= 2;
		}

	}
}
