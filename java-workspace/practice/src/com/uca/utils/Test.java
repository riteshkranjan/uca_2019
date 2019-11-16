package com.uca.utils;

public class Test {

	public static void main(String[] args) {

		List<Integer> l = new ArrayList<>();
		l.add(new Integer(1));
		l.add(new Integer(2));
		l.add(new Integer(3));
		l.add(new Integer(4));
		if (!l.toString().equals("{1,2,3,4}4"))
			throw new AssertionError();
		l.delete(0);
		if (!l.toString().equals("{2,3,4}3"))
			throw new AssertionError(l.toString());
		l.delete(2);
		if (!l.toString().equals("{2,3}2"))
			throw new AssertionError(l.toString());
		l.add(0, new Integer(1));
		l.add(new Integer(4));
		if (!l.toString().equals("{1,2,3,4}4"))
			throw new AssertionError(l.toString());
		l.delete(new Integer(3));
		if (!l.toString().equals("{1,2,4}3"))
			throw new AssertionError(l.toString());
		Iterator<Integer> ite = l.iterator();
		int[] expected = { 1, 2, 4 };
		int i = 0;
		while (ite.hasNext()) {
			Integer t = ite.next();
			if (t.intVal() != expected[i++])
				throw new AssertionError("expecting " + expected[i - 1] + " actual " + t);
		}
		l.add(new Integer(10));
		l.add(new Integer(3));
		if (!l.toString().equals("{1,2,4,10,3}5"))
			throw new AssertionError(l.toString());
		// l.sort();
		Collections.sort(l);
		if (!l.toString().equals("{1,2,3,4,10}5"))
			throw new AssertionError(l.toString());
		Collections.sort(l, new Comparator<Integer>() {

			@Override
			public int compare(Integer t1, Integer t2) {
				return t2.intVal() - t1.intVal();
			}
		});
		if (!l.toString().equals("{10,4,3,2,1}5"))
			throw new AssertionError(l.toString());

		System.out.println("All test cases passed");

	}

	private static class Integer implements Comparable<Integer> {

		private int i;

		public Integer(int i) {
			this.i = i;
		}

		@Override
		public int compareTo(Integer t) {
			return this.i - t.i;
		}

		@Override
		public String toString() {
			return String.valueOf(i);
		}

		public int intVal() {
			return this.i;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + i;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Integer other = (Integer) obj;
			if (i != other.i)
				return false;
			return true;
		}

	}

}
