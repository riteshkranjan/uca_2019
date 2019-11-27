package com.uca.multithreading;

import java.util.concurrent.Callable;

public class Multiplier implements Callable<Integer> {
    int[] arr;
    int index;
    public Multiplier(int[] arr, int index) {
    	this.arr = arr;
    	this.index = index;
    }
	@Override
	public Integer call() throws Exception {
		System.out.println(Thread.currentThread().getName());
		return arr[index]*2;
	}
	
	

}
