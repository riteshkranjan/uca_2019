package com.uca.sort.string;

/**
 *
 * @author Ritesh_Ranjan
 */
public class MSD extends StringSortAlgo {

	@Override
	public void sort(String[] a) {
		int N = a.length;
		String[] aux = new String[N];
		sort(a, 0, N - 1, 0, aux);
	}

	private void sort(String[] a, int lo, int hi, int d, String[] aux) {
		if (hi <= lo + CUTOFF) {
			insertion(a, lo, hi, d);
			return;
		}
		// compute frequency counts
		int[] count = new int[R + 2];
		for (int i = lo; i <= hi; i++) {
			int c = charAt(a[i], d);
			count[c + 2]++;
		}
		// transform counts to indices
		for (int r = 0; r < R + 1; r++) {
			count[r + 1] += count[r];
		}
		// distribute
		for (int i = lo; i <= hi; i++) {
			int c = charAt(a[i], d);
			aux[count[c + 1]++] = a[i];
		}
		// copy back
		for (int i = lo; i <= hi; i++) {
			a[i] = aux[i - lo];
		}
		// Recursion
		for (int r = 0; r < R; r++) {
			sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1, aux);
		}
	}
}
