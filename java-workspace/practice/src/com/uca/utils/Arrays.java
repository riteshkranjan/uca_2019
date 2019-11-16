package com.uca.utils;

public class Arrays {

	public static <T> void sort(T[] data) {
		qsort(data, 0, data.length - 1, null);
	}

	public static <T> void sort(T[] data, Comparator<T> c) {
		qsort(data, 0, data.length - 1, c);
	}

	private static <T> void qsort(T[] data, int L, int R, Comparator<T> c) {
		if (R <= L)
			return;
		int last = L;
		for (int i = L + 1; i <= R; i++) {
			if (compare(data[i], data[L], c) < 0) {
				swap(data, i, ++last);
			}
		}
		swap(data, L, last);
		qsort(data, L, last - 1, c);
		qsort(data, last + 1, R, c);

	}

	@SuppressWarnings("unchecked")
	private static <T> int compare(T t1, T t2, Comparator<T> c) {
		if (c != null)
			return c.compare(t1, t2);
		Comparable<T> c1 = (Comparable<T>) t1;
		return c1.compareTo(t2);
	}

	private static <T> void swap(T[] data, int i, int j) {
		T t = data[i];
		data[i] = data[j];
		data[j] = t;
	}

	@SuppressWarnings("unchecked")
	public static <T> T[] copyOf(T[] data, int size) {
		T[] res = (T[]) new Object[size];
		for (int i = 0; i < size; i++) {
			if (i < data.length)
				res[i] = data[i];
		}
		return res;
	}

}
