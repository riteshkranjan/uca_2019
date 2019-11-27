package com.uca.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int[] a = { 10, 11, 12, 13, 14 };
		ExecutorService e = Executors.newFixedThreadPool(2);
		List<Future<Integer>> response = new ArrayList<>();
		for (int i = 0; i < a.length; i++) {
			Future<Integer> m = e.submit(new Multiplier(a, i));
			response.add(m);
		}

		for (int i = 0; i < a.length; i++) {
			System.out.println(response.get(i).get());
		}
		e.shutdown();
	}
}
