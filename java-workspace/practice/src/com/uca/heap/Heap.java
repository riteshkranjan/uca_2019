package com.uca.heap;

public interface Heap<T extends Comparable<T>> {
	public void insert(T t);
	public T peek();
	public T pop();
	public boolean isEmpty();
	public int size();
	public String getHeapType();
}
