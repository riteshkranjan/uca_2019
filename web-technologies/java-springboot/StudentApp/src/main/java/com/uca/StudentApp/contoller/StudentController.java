package com.uca.StudentApp.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.uca.StudentApp.entity.Student;
import com.uca.StudentApp.service.StudentService;

@Controller
@RequestMapping("/")
public class StudentController {

	@Autowired
	StudentService service;

	@RequestMapping
	public String getAll(Model model) {
		model.addAttribute("students", service.getAllStudents());
		model.addAttribute("student", new Student());
		return "index";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String add(Student s) {
		service.addStudent(s);
		return "redirect:/";
	}

	@RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
	public String deleteStudent(@PathVariable("id") Integer id) {
		service.deleteStudent(id);
		return "redirect:/";
	}

	@RequestMapping(path = "/edit/{id}", method = RequestMethod.POST)
	public String editStudent(@PathVariable("id") Integer id, Student s) {
		service.editStudent(id, s);
		return "redirect:/";
	}

}
