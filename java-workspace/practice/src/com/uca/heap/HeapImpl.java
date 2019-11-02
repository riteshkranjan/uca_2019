package com.uca.heap;

public class HeapImpl<T extends Comparable<T>> implements Heap<T> {
	private T[] a;
	private int size;
	private Type type;
	private final int MAX = 20;

	@SuppressWarnings({ "unchecked" })
	public HeapImpl(Type type) {
		a = (T[]) new Comparable[MAX];
		size = 0;
		this.type = type;
	}

	public HeapImpl() {
		this(Type.MinHeap);
	}

	@Override
	public void insert(T t) {
		if (size() == MAX)
			throw new RuntimeException("overflow");
		a[size++] = t;
		swim(size - 1);
	}

	private void swim(int i) {
		if (i == 0)
			return;
		int cmp = a[i / 2].compareTo(a[i]);
		if ((type == Type.MaxHeap && cmp < 0) || (type == Type.MinHeap && cmp > 0)) {
			swap(i / 2, i);
			swim(i / 2);
		}
	}

	private void swap(int i, int j) {
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	@Override
	public T peek() {
		return isEmpty() ? null : a[0];
	}

	@Override
	public T pop() {
		if (isEmpty())
			throw new RuntimeException("underflow");
		T t = peek();
		swap(0, size - 1);
		size--;
		sink(0);
		return t;
	}

	private void sink(int i) {
		if (i > size())
			return;
		int L = i * 2;
		int R = L + 1;
		int maxOrMin = i;
		if (L < size) {
			int cmp = a[L].compareTo(a[i]);
			if ((type == Type.MaxHeap && cmp > 0) || (type == Type.MinHeap && cmp < 0))
				maxOrMin = L;
		}

		if (R < size) {
			int cmp = a[R].compareTo(a[i]);
			if ((type == Type.MaxHeap && cmp > 0) || (type == Type.MinHeap && cmp < 0))
				maxOrMin = R;
		}
		if (maxOrMin != i) {
			swap(maxOrMin, i);
			sink(maxOrMin);
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	public String getPrintable() {
		if (size == 0)
			return null;
		StringBuilder sb = new StringBuilder("[");
		for (int i = 0; i < size - 1; i++)
			sb.append(a[i] + ",");
		sb.append(a[size - 1]).append("]");
		return sb.toString();
	}

	@Override
	public String toString() {
		return "HeapImpl [a=" + getPrintable() + ", size=" + size + ", type=" + type + "]";
	}

	@Override
	public String getHeapType() {
		return type.name();
	}
}
