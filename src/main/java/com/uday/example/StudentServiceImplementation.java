package com.uday.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

@Service("StudentService")
public class StudentServiceImplementation implements StudentService {
	
	private static final AtomicLong counter = new AtomicLong();
	
	private static List<Student> students;
	
	

static{
		students= populateDummyStudents();
}

	
	
	@Override
	public Student findById(long id) {
		// TODO Auto-generated method stub
		
		for(Student student : students){
			if(student.getId() == id){
				return student;
			}
		}
		
		return null;
	}

	@Override
	public Student findByName(String name) {
		// TODO Auto-generated method stub
		
		for(Student student : students){
			if(student.getName().equalsIgnoreCase(name)){
				return student;
			}
		}
		
		return null;
	}

	@Override
	public void saveStudent(Student student) {
		// TODO Auto-generated method stub

		student.setId(counter.incrementAndGet());
		students.add(student);
		
	}

	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub

		int index = students.indexOf(student);
		students.set(index, student);
	}

	@Override
	public void deleteStudentById(long id) {
		// TODO Auto-generated method stub
		
		for (Iterator<Student> iterator = students.iterator(); iterator.hasNext(); ) {
			Student student = iterator.next();
		    if (student.getId() == id) {
		        iterator.remove();
		    }
		}

	}

	@Override
	public List<Student> findAllStudents() {
		// TODO Auto-generated method stub
		
		return students;
	}

	@Override
	public void deleteAllStudents() {
		// TODO Auto-generated method stub
		
		students.clear();
	}

	@Override
	public boolean isStudentExist(Student student) {
		// TODO Auto-generated method stub
		
		return findByName(student.getName())!=null;
	}
	
	private static List<Student> populateDummyStudents(){
		List<Student> students = new ArrayList<Student>();
		students.add(new Student(counter.incrementAndGet(),"Uday",44, 4));
		students.add(new Student(counter.incrementAndGet(),"Charith",11, 5));
		students.add(new Student(counter.incrementAndGet(),"Jashwanth",15, 3));
		students.add(new Student(counter.incrementAndGet(),"David",22, 1));
		students.add(new Student(counter.incrementAndGet(),"Rahul",33, 2));
		
		System.out.println("Dummy Student List : " + students.toString());
		
		return students;
	}
	

}
