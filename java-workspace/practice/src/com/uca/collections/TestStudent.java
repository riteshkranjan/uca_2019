package com.uca.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestStudent {
	public static void main(String[] args) {
		Student s = new Student(4, "abc", 4.0f);
		List<Student> list = new ArrayList<>();
		list.add(s);
		list.add(new Student(2, "xyz", 3.0f));
		list.add(new Student(6, "mno", 6.0f));
		list.add(new Student(1, "def", 4.0f));
		for (Student d : list)
			System.out.println(d);
		System.out.println("*****************");
		MyCollections<Student> col = new MyCollections<>();
		col.sort(list);
		//Collections.sort(list);
		for (Student d : list)
			System.out.println(d);

		System.out.println("*****************");
		System.exit(0);
		//Collections.sort(list, new StudentIdComparator());
		col.sort(list,new StudentIdComparator());
		for (Student d : list)
			System.out.println(d);

		System.out.println("*****************");
		//Collections.sort(list, new StudentCGPAComparator());
		col.sort(list, new StudentCGPAComparator());
		for (Student d : list)
			System.out.println(d);

		System.out.println("*****************");
		Collections.sort(list, new Comparator<Student>() {

			@Override
			public int compare(Student arg0, Student arg1) {
				int c = (int) (arg0.cgpa - arg1.cgpa);
				return c == 0 ? arg0.id - arg1.id : c;
			}
		});
		for (Student d : list)
			System.out.println(d);
	}

}
