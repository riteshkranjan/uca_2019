package com.uca.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class StreamAPI {

	public static void main(String[] args) {
		Map<String, Integer> items = new HashMap<String, Integer>() {
			private static final long serialVersionUID = 1L;

			{
				put("a", 25);
				put("b", 25);
				put("c", 20);
				put("d", 30);
				put("e", 20);
			}
		};

		printUsingToString(items);

		printUsingStreamAIP(items);

		sortUsingStreamAPI(items);

		sortUsingIterator(items);

	}

	private static void printUsingToString(Map<String, Integer> items) {
		System.out.println("printUsingToString");
		System.out.println(items.toString());

	}

	private static void printUsingStreamAIP(Map<String, Integer> items) {
		System.out.println("printUsingStreamAIP");
		items.entrySet().stream().forEach(System.out::println);
	}

	private static void sortUsingIterator(Map<String, Integer> items) {
		System.out.println("sortUsingIterator");
		Map<Integer, List<String>> reverseMap = new TreeMap<>();
		Iterator<Entry<String, Integer>> iterator = items.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Integer> next = iterator.next();
			System.out.println(next.getKey() + " " + next.getValue());
		}

		Iterator<String> ite = items.keySet().iterator();
		while (ite.hasNext()) {
			String key = ite.next();
			int val = items.get(key);
			if (reverseMap.get(val) == null) {
				reverseMap.put(val, new ArrayList<String>() {
					private static final long serialVersionUID = 1L;

					{
						add(key);
					}
				});
			} else {
				reverseMap.get(val).add(key);
			}
		}
		reverseMap.entrySet().stream().forEach(System.out::println);
	}

	private static void sortUsingStreamAPI(Map<String, Integer> items) {
		System.out.println("sortUsingStreamAPI");
		Map<String, Integer> sorted = items.entrySet().stream().sorted(Map.Entry.comparingByValue())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

		sorted.entrySet().stream().forEach(System.out::println);
	}

}
