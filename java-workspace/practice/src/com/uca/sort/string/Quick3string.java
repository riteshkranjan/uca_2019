package com.uca.sort.string;

public class Quick3string extends StringSortAlgo {

	private void sort(String[] a, int lo, int hi, int d) {
		// cutoff to insertion sort for small subarrays
		if (hi <= lo + CUTOFF) {
			insertion(a, lo, hi, d);
			return;
		}
		int lt = lo, gt = hi;
		int v = charAt(a[lo], d);
		int i = lo + 1;
		while (i <= gt) {
			int t = charAt(a[i], d);
			if (t < v)
				exch(a, lt++, i++);
			else if (t > v)
				exch(a, i, gt--);
			else
				i++;
		}
		sort(a, lo, lt - 1, d);
		if (v >= 0)
			sort(a, lt, gt, d + 1);
		sort(a, gt + 1, hi, d);
	}

	@Override
	protected void sort(String[] a) {
		sort(a, 0, a.length - 1, 0);
	}
}
