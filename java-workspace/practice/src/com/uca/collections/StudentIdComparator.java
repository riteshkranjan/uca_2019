package com.uca.collections;

import java.util.Comparator;

public class StudentIdComparator implements Comparator<Student> {

	@Override
	public int compare(Student arg0, Student arg1) {
		return arg0.id - arg1.id;
	}

}
