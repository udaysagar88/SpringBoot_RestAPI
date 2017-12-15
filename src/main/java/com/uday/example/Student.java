package com.uday.example;

public class Student {

	private long id;
	
	private String name;
	
	private int age;
	
	private int rank;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public Student(long id, String name, int age, int rank){
		this.id = id;
		this.name = name;
		this.age = age;
		this.rank = rank;
	}
	
	public Student(){
		id=0;
	}
	
}
