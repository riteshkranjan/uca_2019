package com.uca.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SMSSender implements Runnable {
	private int num;

	public SMSSender(int num) {
		this.num = num;
	}

	@Override
	public void run() {
		System.out.println("sending sms to" + num+ " by "+Thread.currentThread().getName());
		
	}

	public static void main(String[] args) throws InterruptedException {
		int[] phones = { 1, 2, 3, 4, 5 };
		ExecutorService e = Executors.newFixedThreadPool(3);
		for(int ph : phones) {
			e.submit(new SMSSender(ph));
		}
		e.shutdown();
		System.out.println("i m done with sending sms");

	}

}
