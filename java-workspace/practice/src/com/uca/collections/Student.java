package com.uca.collections;

public class Student implements Comparable<Student> {

	public int id;
	public String name;
	public float cgpa;

	public Student(int id, String name, float cgpa) {
		this.id = id;
		this.name = name;
		this.cgpa = cgpa;
	}

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", cgpa=" + cgpa + "]";
	}

	@Override
	public int compareTo(Student o) {
		return this.name.compareTo(o.name);
	}
}
