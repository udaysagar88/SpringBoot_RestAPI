//Author Uday

package com.uday.example;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/api")
public class MyRestAPIController {
	
	@Autowired
	StudentService studentService;
	
	public static final Logger logger = LoggerFactory.getLogger(MyRestAPIController.class);

	// -------------------Retrieve All Students---------------------------------------------

		@RequestMapping(value = "/student/", method = RequestMethod.GET)
		public ResponseEntity<List<Student>> listAllStudents() {
			List<Student> students = studentService.findAllStudents();
			if (students.isEmpty()) {
				return new ResponseEntity(HttpStatus.NO_CONTENT);
				
			}
			return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
		}
		
		
		// -------------------Retrieve Single Student------------------------------------------

		@SuppressWarnings("unchecked")
		@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
		public ResponseEntity<?> getUser(@PathVariable("id") long id) {
			logger.info("Fetching Student with id {}", id);
			Student student = studentService.findById(id);
			if (student == null) {
				logger.error("student with id {} not found.", id);
				return new ResponseEntity("User with id " + id + " not found", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Student>(student, HttpStatus.OK);
		}
		
		// ------------------- Update Student ------------------------------------------------

		@RequestMapping(value = "/student/update/{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> updateStudent(@PathVariable("id") long id, @RequestBody Student student) {
			logger.info("Updating student with id {}", id);

			Student currentStudent = studentService.findById(id);

			if (currentStudent == null) {
				logger.error("Unable to update. Student with id {} not found.", id);
				return new ResponseEntity("Unable to upate. Student with id " + id + " not found.",
						HttpStatus.NOT_FOUND);
			}

			currentStudent.setName(student.getName());
			currentStudent.setAge(student.getAge());
			currentStudent.setRank(student.getRank());

			studentService.updateStudent(currentStudent);
			return new ResponseEntity<Student>(currentStudent, HttpStatus.OK);
		}

		// ------------------- Delete a Student-----------------------------------------

		@RequestMapping(value = "/student/delete/{id}", method = RequestMethod.DELETE)
		public ResponseEntity<?> deleteStudent(@PathVariable("id") long id) {
			logger.info("Fetching & Deleting Student with id {}", id);

			Student student = studentService.findById(id);
			if (student == null) {
				logger.error("Unable to delete. Student with id {} not found.", id);
				return new ResponseEntity("Unable to delete. Student with id " + id + " not found.",
						HttpStatus.NOT_FOUND);
			}
			studentService.deleteStudentById(id);
			return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
		}

		// ------------------- Delete All Students-----------------------------

		@RequestMapping(value = "/student/deleteall/", method = RequestMethod.DELETE)
		public ResponseEntity<Student> deleteAllStudents() {
			logger.info("Deleting All students");

			studentService.deleteAllStudents();
			return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
		}
		
		// -------------------Create a Student-------------------------------------------

		@RequestMapping(value = "/student/create/", method = RequestMethod.POST)
		public ResponseEntity<?> createStudent(@RequestBody Student student, UriComponentsBuilder ucBuilder) {
			logger.info("Creating Student : {}", student);

			if (studentService.isStudentExist(student)) {
				logger.error("Unable to create. A Student with name {} already exist", student.getName());
				return new ResponseEntity("Unable to create. A Student with name " + 
				student.getName() + " already exist.", HttpStatus.CONFLICT);
			}
			studentService.saveStudent(student);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/student/{id}").buildAndExpand(student.getId()).toUri());
			return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		}
		
		
}
