package com.uday.example;

import java.util.List;

public interface StudentService {
	
	Student findById(long id);
	
	Student findByName(String name);
	
	void saveStudent(Student student);
	
	void updateStudent(Student student);
	
	void deleteStudentById(long id);

	List<Student> findAllStudents();
	
	void deleteAllStudents();
	
	boolean isStudentExist(Student student);
	

}
