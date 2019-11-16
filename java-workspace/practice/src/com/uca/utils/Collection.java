package com.uca.utils;

public interface Collection<T> extends Iterable<T> {

	public int size();

	public void clear();

	public boolean isEmpty();

	public boolean add(T t);

	public boolean delete(T t);

	public T[] toArray();

}
