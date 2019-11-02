package com.uca.collections;

import java.util.Comparator;

public class StudentCGPAComparator implements Comparator<Student> {

	@Override
	public int compare(Student arg0, Student arg1) {
		return (int)(arg0.cgpa - arg1.cgpa);
	}

}
