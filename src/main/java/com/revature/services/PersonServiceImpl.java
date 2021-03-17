package com.revature.services;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.PersonDao;
import com.revature.pojo.Person;

public class PersonServiceImpl implements PersonService {

	Logger log = Logger.getRootLogger();
	
	private PersonDao persondao;
	
	public PersonServiceImpl(PersonDao persondao) {
		super();
		this.persondao = persondao;
	}

	public PersonDao getPersondao() {
		return persondao;
	}

	public void setPersondao(PersonDao persondao) {
		this.persondao = persondao;
	}

	@Override
	public Person addPersontoTree(Person person) {
		log.trace("Add person to tree method called");
		persondao.createPerson(person);
		return person;
	}

	@Override
	public void removePerson(Person person) {
		log.trace("Remove person method called");
		persondao.deletePerson(person);
	}

	@Override
	public List<Person> displayfamilyTree() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Person> getNuclearFamily() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existingPerson(Person person) {
		try {
			if(persondao.getPersonByName(person.getFirstName(), person.getLastName()) != null) {
				return true;
			}
		} catch(Exception e) {
			log.error("Person doesn't exist");
		}
		return false;
	}

}
