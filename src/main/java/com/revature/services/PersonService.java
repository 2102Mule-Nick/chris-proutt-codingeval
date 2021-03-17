package com.revature.services;

import java.util.List;

import com.revature.pojo.Person;

public interface PersonService {
	public void addPersontoTree(Person person);
	
	public void removePerson(Person person);
	
	public List<Person> displayfamilyTree();
	
	public List<Person> getNuclearFamily();
}
