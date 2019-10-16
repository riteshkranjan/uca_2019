package com.uca.StudentApp.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.StudentApp.dao.StudentRepo;
import com.uca.StudentApp.entity.Student;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentRepo repo;
    
	@Override
	public List<Student> getAllStudents() {
		return repo.findAll();
	}

	@Override
	public void addStudent(Student s) {
		s.setInsertedDate(new Date());
		repo.save(s);
	}

	@Override
	public void deleteStudent(Integer id) {
		repo.delete(getStudent(id));
	}

	@Override
	public void editStudent(Integer id, Student s) {
		Student existing = getStudent(id);
		existing.setEmail(s.getEmail());
		existing.setName(s.getName());
		existing.setGpa(s.getGpa());
		existing.setInsertedDate(new Date());
		repo.save(existing);
	}
	
	private Student getStudent(Integer id) {
		return repo.getOne(id);
	}

}
