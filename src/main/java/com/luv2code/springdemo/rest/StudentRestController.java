package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	
	private List<Student> theStudents;
	
	// Define @PostConstruct to load the student data ONLY ONCE
	@PostConstruct
	public void loadData() {
		
		theStudents = new ArrayList<>();
		
		theStudents.add(new Student("Tom","Brady"));
		theStudents.add(new Student("Patrick","Mahomes"));
		theStudents.add(new Student("Lamar","Jackson"));
	}
	
	// Define endpoint for "/students" - return list of all students
	@GetMapping("/students")
	public List<Student> getStudents(){
		return theStudents;
	}
	
	// Define endpoint for "/students/{studentId}" - return student at index
	@GetMapping("/students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		// Check that studentId input is appropriate
		if( (studentId >= theStudents.size()) || (studentId < 0) ) {
			throw new StudentNotFoundException("Student id not found - " + studentId);
		}
		
		return theStudents.get(studentId);
	}
	

}
