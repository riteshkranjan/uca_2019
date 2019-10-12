package com.uca.trees;

import java.util.Random;

public class TestBst {

	public static void main(String[] args) {
		LLRBT rbt = new LLRBT();
		BST bst = new BST();
		System.out.println("testing with random input");
		Random r = new Random();
		for (int i = 10000; i > 0; i--) {
			int key = r.nextInt();
			bst.insert(key);
		    rbt.insert(key);
		}
		System.out.println("height of BST is : "+bst.height());
		System.out.println("height of RBT is : "+rbt.height());
		
		System.out.println("testing with sorted input");
		rbt = new LLRBT();
		bst = new BST();
		for (int i = 10000; i > 0; i--) {
			
			bst.insert(i);
		    rbt.insert(i);
		}
		System.out.println("height of BST is : "+bst.height());
		System.out.println("height of RBT is : "+rbt.height());

	}

}
