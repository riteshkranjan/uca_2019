package com.uca.multithreading;

public class Counter  implements Runnable {
	private int c = 0;

	public int getC() {
		return c;
	}

	// private String m = "somethign";

	private synchronized void increment() {
		c++;
		System.out.println(Thread.currentThread().getName() + ":" + c);

	}

	@Override
	public void run() {
		increment();

	}
}
