package com.uca.util;

import java.util.Arrays;

public class TestSort {
	int[] input  = new int[] {5,3,4,7,1};
	public void sort() {
		Arrays.parallelSort(input);
	}
	
	@Sort
	public void prepareData() {
		Arrays.stream(input).forEach(System.out::println);
		
	}

}
