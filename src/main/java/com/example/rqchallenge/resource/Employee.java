package com.example.rqchallenge.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee {

	@JsonProperty("id")
	private String id;

	@JsonProperty("employee_name")
	private String name;

	@JsonProperty("employee_salary")
	private int salary;

	@JsonProperty("employee_age")
	private int age;

	@JsonProperty("profile_image")
	private String image;

	public Employee() {

	}

	public Employee(String id, String name, int salary, int age, String image) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.age = age;
		this.image = image;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the salary
	 */
	public int getSalary() {
		return salary;
	}

	/**
	 * @param salary the salary to set
	 */
	public void setSalary(int salary) {
		this.salary = salary;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

}
