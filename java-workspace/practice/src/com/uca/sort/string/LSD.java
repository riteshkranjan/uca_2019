package com.uca.sort.string;

/**
 *
 * @author Ritesh_Ranjan
 */
public class LSD extends StringSortAlgo {

	@Override
	protected void sort(String[] a) {
		int W = a[0].length();
		sort(a, W);
	}

	private void sort(String[] a, int W) {
		int N = a.length;
		String[] aux = new String[N];
		for (int d = W - 1; d >= 0; d--) {
			// compute frequency counts
			int[] count = new int[R + 1];
			for (int i = 0; i < N; i++) {
				count[a[i].charAt(d) + 1]++;
			}
			// compute cumulates
			for (int r = 0; r < R; r++) {
				count[r + 1] += count[r];
			}
			// move data
			for (int i = 0; i < N; i++) {
				aux[count[a[i].charAt(d)]++] = a[i];
			}
			// copy back
			for (int i = 0; i < N; i++) {
				a[i] = aux[i];
			}
		}
	}
}
