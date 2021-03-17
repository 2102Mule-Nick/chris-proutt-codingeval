/**
 * 
 */
package com.revature.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.dao.PersonDao;
import com.revature.dao.PersonDaoImpl;
import com.revature.pojo.Person;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;

class PersonServiceImplTest {

	private static Person person;
	
	private static PersonDao persondao;
	
	private static PersonService service;
	
	@BeforeEach
	private void setUp() {
		person = new Person();
		persondao = new PersonDaoImpl();
		service = new PersonServiceImpl(persondao);
	}

	@AfterEach
	private void tearDown() {
		person = null;
		persondao = null;
		service = null;
	}

	@Test
	void testAddPersontoTree() {
		assertEquals(service.addPersontoTree(person), person);
	}

	@Test
	void testRemovePerson() {
		fail("Not yet implemented");
	}

	@Test
	void testDisplayfamilyTree() {
		fail("Not yet implemented");
	}

	@Test
	void testGetNuclearFamily() {
		fail("Not yet implemented");
	}

}
