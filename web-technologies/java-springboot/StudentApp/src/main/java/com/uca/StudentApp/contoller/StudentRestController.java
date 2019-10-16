package com.uca.StudentApp.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uca.StudentApp.entity.Student;
import com.uca.StudentApp.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentRestController {
	
	@Autowired
	StudentService service;

	@RequestMapping
	public List<Student> getAll() {
		return service.getAllStudents();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String add(@RequestBody Student s) {
		service.addStudent(s);
		return "data added successfully";
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public String deleteStudent(@PathVariable("id") Integer id) {
		service.deleteStudent(id);
		return "data deleted successfully";
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.PATCH)
	public String editStudent(@PathVariable("id") Integer id, @RequestBody Student s) {
		service.editStudent(id, s);
		return "data updated successfully";
	}

}
