package com.uca.utils;

public interface List<T> extends Collection<T> {

	public T get(int i);

	public void delete(int i);

	public void add(int i, T t);

	default void sort() {
		this.sort(null);
	}

	default void sort(Comparator<T> c) {
		T[] array = this.toArray();
		Arrays.sort(array, c);
		this.clear();
		for (T t : array) {
			this.add(t);
		}
	}

}
