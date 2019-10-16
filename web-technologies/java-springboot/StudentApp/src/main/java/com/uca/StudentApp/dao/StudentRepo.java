package com.uca.StudentApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.StudentApp.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{

}
