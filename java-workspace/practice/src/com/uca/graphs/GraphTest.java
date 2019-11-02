package com.uca.graphs;

import java.util.Stack;

public class GraphTest {

	public static void main(String[] args) {
		Graph g  = new Graph(8);
		g.addEdge(5, 3);
		g.addEdge(5, 2);
		g.addEdge(5, 4);
		g.addEdge(1, 4);
		g.addEdge(1, 0);
		g.addEdge(1, 2);
		g.addEdge(6, 7);
		System.out.println(g.degree(4));
		System.out.println(g.degree(3));
		g.dfs();
		System.out.println(g.isConnected(5));
		System.out.println(g.isConnected(6));
		Stack<Integer> path = g.pathTo(5);
		while(!path.isEmpty())
		   System.out.print(path.pop()+"->");
		System.out.println();
	}

}
