package com.uca.sort.string;

public abstract class StringSortAlgo {

	protected abstract void sort(String[] a);

	protected final int R = 256; // total 256 characters

	protected final int CUTOFF = 2; // cutoff for insertion sort

	protected void print(String[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println("\n--------------------");
	}

	protected void insertion(String[] a, int lo, int hi, int d) {
		for (int i = lo; i <= hi; i++) {
			for (int j = i; j > lo && less(a[j], a[j - 1], d); j--) {
				exch(a, j, j - 1);
			}
		}
	}

	protected void exch(String[] a, int i, int j) {
		String temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	protected boolean less(String v, String w, int d) {
		for (int i = d; i < Math.min(v.length(), w.length()); i++) {
			if (v.charAt(i) < w.charAt(i))
				return true;
			if (v.charAt(i) > w.charAt(i))
				return false;
		}
		return v.length() < w.length();
	}

	protected int charAt(String s, int d) {
		assert d >= 0 && d <= s.length();
		if (d == s.length())
			return -1;
		return s.charAt(d);
	}
}
