package com.uca.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

	Map<Integer, Integer> lru;

	public LRUCache(int capacity) {
		lru = new LinkedHashMap<Integer, Integer>(capacity,0.75f,true) {
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
				return lru.size() > capacity;
			};
		};
	}

	public int get(int key) {
		System.out.println("going to get key "+key);
		return lru.getOrDefault(key, -1);
	}

	public void set(int key, int val) {
		System.out.println("setting key "+key);
		lru.put(key, val);
	}

	public static void main(String[] args) {
		LRUCache cache = new LRUCache(3);
		cache.set(1, 10);
		cache.set(2, 20);
		cache.set(3, 30);
		cache.print();
		//System.out.println(cache.get(2));
		System.out.println(cache.get(1));
		System.out.println(cache.get(3));
		cache.set(4, 40);
		cache.print();
		System.out.println(cache.get(2));
		System.out.println(cache.get(1));
		System.out.println(cache.get(3));
		System.out.println(cache.get(4));
	}

	private void print() {
		System.out.println(lru.toString());

	}

}
