package com.uca.StudentApp.service;

import java.util.List;

import com.uca.StudentApp.entity.Student;

public interface StudentService {

	List<Student> getAllStudents();

	void addStudent(Student s);

	void deleteStudent(Integer id);

	void editStudent(Integer id, Student s);

}
