package com.revature.dao;

import java.util.List;

import com.revature.pojo.Person;

public interface PersonDao {
	
	public void createPerson(Person person);
	
	public void updateRelationship(Person child, Person parent, String newRelationship);
	
	public Person getPersonByName(String firstName, String lastName);
	
	public void deletePerson(Person person);
	
	public List<Person> displayFamilyTree();
}
