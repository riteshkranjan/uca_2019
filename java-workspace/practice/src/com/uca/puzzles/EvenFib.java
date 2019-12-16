package com.uca.puzzles;

public class EvenFib {

	public static void main(String[] args) {
		System.out.println(sumEvenFib(1000));
		System.out.println(sumEvenFibRecursive(1000, 0, 2));
	}

	private static int sumEvenFibRecursive(int max, int l, int r) {
		return r > max ? 0 : r + sumEvenFibRecursive(max, r, 4 * r + l);
	}

	private static int sumEvenFib(int max) {
		int last = 0;
		int curr = 2;
		int sum = 0;
		int temp = 0;
		while (curr < max) {
			sum += curr;
			temp = curr;
			curr = 4 * curr + last;
			last = temp;
		}
		return sum;
	}

}
