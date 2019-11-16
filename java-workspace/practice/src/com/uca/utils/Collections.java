package com.uca.utils;

public class Collections {

	public static <T extends Comparable<? super T>> void sort(List<T> l) {
		l.sort();
	}

	public static <T extends Comparable<? super T>> void sort(List<T> l, Comparator<T> c) {
		l.sort(c);
	}

}
