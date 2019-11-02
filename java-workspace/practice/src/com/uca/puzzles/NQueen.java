package com.uca.puzzles;

import java.util.Arrays;
import java.util.Scanner;

public class NQueen {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("enter board size :");
		int size = sc.nextInt();
		int[] solution = solve(size);
		if (solution != null) {
			System.out.print("  ");
			for (int i = 0; i < size; i++)
				System.out.print(i);
			System.out.println();
			for (int i = 0; i < size; i++) {
				System.out.print(i + ":");
				for (int j = 0; j < size; j++)
					System.out.print(solution[j] == i ? "Q" : " ");
				System.out.println();
			}
		}
		sc.close();
	}

	private static int[] solve(int size) {
		int[] chess = new int[size];
		Arrays.fill(chess, -1);
		if (solve(chess, 0, size)) {
			return chess;
		}
		System.out.println("no solution");
		return null;

	}

	private static boolean solve(int[] chess, int col, int size) {
		if (col == size)
			return true;
		for (int i = 0; i < size; i++) {
			chess[col] = i;
			if (validMove(chess, col, i) && solve(chess, col + 1, size))
				return true;
		}
		chess[col] = -1;
		return false;
	}

	private static boolean validMove(int[] chess, int col, int val) {
		int c = 1;
		for (int i = col - 1; i >= 0; i--) {
			if (chess[i] == val)
				return false;
			if (chess[i] == val - c || chess[i] == val + c)
				return false;
			c++;
		}
		return true;
	}
}
