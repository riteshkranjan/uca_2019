package com.uca.heap;

public class MedianOfStream {

	private Heap<Integer> maxHeap = new HeapImpl<>(Type.MaxHeap);
	private Heap<Integer> minHeap = new HeapImpl<>(Type.MinHeap);

	public void insert(Integer t) {
		if (isEmpty()) {
			System.out.println("inserting : " + t + " into " + minHeap.getHeapType());
			minHeap.insert(t);
			return;
		}
		if (minHeap.peek() < t) {
			insertAndBalance(t, minHeap, maxHeap);
		} else {
			insertAndBalance(t, maxHeap, minHeap);
		}
	}

	private void insertAndBalance(Integer t, Heap<Integer> h1, Heap<Integer> h2) {
		System.out.println("inserting : " + t + " into " + h1.getHeapType());
		h1.insert(t);
		if (h1.size() - h2.size() > 1) {
			int e = h1.pop();
			System.out.println("Balancing : deleted " + e + " from " + h1.getHeapType());
			h2.insert(e);
			System.out.println("Balancing : inserting " + e + " into " + h2.getHeapType());
		}
		System.out.println("**********");
	}

	public Integer getMedian() {
		if (isEmpty())
			return null;
		if (maxHeap.size() > minHeap.size())
			return maxHeap.peek();
		if (maxHeap.size() < minHeap.size())
			return minHeap.peek();
		return (maxHeap.peek() + minHeap.peek()) / 2;
	}

	public boolean isEmpty() {
		return maxHeap.isEmpty() && minHeap.isEmpty();
	}

	public int size() {
		return maxHeap.size() + minHeap.size();
	}

	@Override
	public String toString() {
		return "MedianOfStream [maxHeap=" + maxHeap + ", minHeap=" + minHeap + "]";
	}

}
