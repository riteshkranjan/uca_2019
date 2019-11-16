package com.uca.utils;

public class ArrayList<T> implements List<T> {

	private T[] data;
	private int MAX_SIZE = 2;
	private int size = 0;

	public ArrayList() {
		this(10);

	}

	@SuppressWarnings("unchecked")
	public ArrayList(int size) {
		this.MAX_SIZE = size;
		data = (T[]) new Object[size];
	}

	@Override
	public int size() {
		return size;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		size = 0;
		MAX_SIZE = 2;
		data = (T[]) new Object[MAX_SIZE];
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean add(T t) {
		data[size++] = t;
		resize();
		return true;
	}

	private void resize() {
		if (size >= MAX_SIZE / 2) {
			MAX_SIZE *= 2;
			data = Arrays.copyOf(data, MAX_SIZE);
		} else {
			if (size <= MAX_SIZE / 4) {
				MAX_SIZE /= 2;
				data = Arrays.copyOf(data, MAX_SIZE);
			}
		}
	}

	@Override
	public boolean delete(T t) {
		int i = 0;
		for (; i < size; i++) {
			if (data[i].equals(t))
				break;
		}
		if (i > size) {
			return false;
		}
		delete(i);
		return true;
	}

	@Override
	public T[] toArray() {
		return Arrays.copyOf(data, size);
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private int i = 0;

			@Override
			public boolean hasNext() {
				return i < size;
			}

			@Override
			public T next() {
				T t = data[i];
				i++;
				return t;
			}
		};
	}

	@Override
	public T get(int i) {
		if (i > size)
			throw new IndexOutOfBoundException();
		return data[i];
	}

	@Override
	public void delete(int i) {
		if (i > size)
			throw new IndexOutOfBoundException();
		for (; i < size - 1; i++)
			data[i] = data[i + 1];

		size--;
		resize();

	}

	@Override
	public void add(int i, T t) {
		if (i > size)
			throw new IndexOutOfBoundException();
		size++;
		for (int j = size - 1; j > i; j--)
			data[j] = data[j - 1];
		data[i] = t;
		resize();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		for (int i = 0; i < size; i++) {
			sb.append(data[i]);
			if (i < size - 1)
				sb.append(",");
		}
		sb.append("}").append(size);
		return sb.toString();
	}
}
