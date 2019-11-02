package com.uca.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestHeap {

	public static void main(String[] args) {
		Heap<Integer> maxHeap = new HeapImpl<>(Type.MaxHeap);
		maxHeap.insert(5);
		maxHeap.insert(3);
		maxHeap.insert(6);
		maxHeap.insert(2);
		if (maxHeap.peek() != 6 || maxHeap.pop() != 6 || maxHeap.peek() != 5)
			throw new AssertionError();
		maxHeap.insert(1);
		if (maxHeap.isEmpty() || maxHeap.size() != 4)
			throw new AssertionError(maxHeap.isEmpty() + " " + maxHeap.size());
		if (maxHeap.pop() != 5 || maxHeap.size() != 3)
			throw new AssertionError();

		Heap<Integer> minHeap1 = new HeapImpl<>(Type.MinHeap);
		minHeap1.insert(5);
		minHeap1.insert(3);
		minHeap1.insert(6);
		minHeap1.insert(2);
		if (minHeap1.peek() != 2 || minHeap1.pop() != 2 || minHeap1.peek() != 3)
			throw new AssertionError();
		if (minHeap1.isEmpty() || minHeap1.size() != 3)
			throw new AssertionError(minHeap1.isEmpty() + " " + minHeap1.size());
		if (minHeap1.pop() != 3 || minHeap1.size() != 2)
			throw new AssertionError();

		Heap<Integer> minHeap2 = new HeapImpl<>();
		minHeap2.insert(5);
		minHeap2.insert(3);
		minHeap2.insert(6);
		minHeap2.insert(2);
		if (minHeap2.peek() != 2 || minHeap2.pop() != 2 || minHeap2.peek() != 3)
			throw new AssertionError();
		if (minHeap2.isEmpty() || minHeap2.size() != 3)
			throw new AssertionError(minHeap2.isEmpty() + " " + minHeap2.size());
		if (minHeap2.pop() != 3 || minHeap2.size() != 2)
			throw new AssertionError();

		MedianOfStream mos = new MedianOfStream();
		List<Integer> list = new ArrayList<>();

		Random r = new Random();
		for (int i = 0; i < 20; i++) {
			int e = r.nextInt(100);
			list.add(e);
			mos.insert(e);
			int median = getMedian(list);
			if (mos.getMedian() != median)
				throw new AssertionError(list +" " + mos.toString());
		}

		System.out.println("All test cases passed!!");

	}

	private static int getMedian(List<Integer> list) {
		Collections.sort(list);
		if (list.size() % 2 == 0)
			return (list.get(list.size() / 2) + list.get(list.size() / 2 - 1)) / 2;
		return list.get(list.size() / 2);
	}

}
