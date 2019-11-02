package com.uca.graphs;

import java.util.Stack;

public class Graph {
	private int V;
	private int E;
	private Bag[] adj;
	private boolean[] marked;
	private int[] edgeTo;

	public Graph(int V) {
		this.V = V;
		adj = new Bag[V];
		for (int i = 0; i < V; i++)
			adj[i] = new Bag();
	}

	public void dfs() {
		marked = new boolean[V];
		edgeTo = new int[V];
		marked[0] = true;
		edgeTo[0] = 0;
		dfs(0);
	}

	private void dfs(int v) {
		for (int w : adj(v)) {
			if (!marked[w]) {
				marked[w] = true;
				edgeTo[w] = v;
				dfs(w);
			}
		}
	}

	public boolean isConnected(int v) {
		if (marked == null)
			throw new RuntimeException("run dfs first");
		return marked[v];
	}

	public Stack<Integer> pathTo(int v) {
		if (marked == null)
			throw new RuntimeException("run dfs first");
		if (!marked[v])
			return null;
		Stack<Integer> s = new Stack<>();
		while (v != 0) {
			s.push(v);
			v = edgeTo[v];
		}
		s.push(0);
		return s;
	}

	public void addEdge(int v, int w) {
		adj[w].insert(v);
		adj[v].insert(w);
		E++;
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	public Iterable<Integer> adj(int v) {
		return adj[v];
	}

	public int degree(int v) {
		return adj[v].size();
	}

	public float avgDegree() {
		int avg = 0;
		for (int i = 0; i < V; i++) {
			avg += degree(i);
		}
		return avg / V;
	}

	public int maxDegree() {
		int max = 0;
		for (int i = 0; i < V; i++) {
			int d = degree(i);
			if (d > max)
				max = d;
		}
		return max;
	}

}
