package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.pojo.Person;
import com.revature.utilities.ConnectionFactory;

public class PersonDaoImpl implements PersonDao {

	Logger log = Logger.getRootLogger();
	
	@Override
	public void createPerson(Person person) {
		log.trace("create person method called");
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "insert into persons (first_name, last_name, parent_first_name, parent_last_name) values (?, ?, ?, ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getFirstName());
			pstmt.setString(2, person.getLastName());
			pstmt.setString(3, person.getParentFirstName());
			pstmt.setString(4, person.getParentLastName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			log.error("Couldn't connect to the database");
		}
		
		String sql1 = "insert into parents (parent_id, child_id) values ((select person_id from persons where first_name = ? and last_name = ?), (select person_id from persons where first_name = ? and last_name = ?), ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql1);
			pstmt.setString(1, person.getParentFirstName());
			pstmt.setString(2, person.getParentLastName());
			pstmt.setString(3, person.getFirstName());
			pstmt.setString(4, person.getLastName());
		} catch(SQLException e) {
			log.error("Couldn't connect to the database. Person not inserted into parents table");
		}
	}

	@Override
	public void updateRelationship(Person child, Person parent, String newRelationship) {
		log.trace("Update persons method called");

		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "update parents set relationship = ? where child_id = (select person_id from persons where first_name = ? and last_name = ?) and (select person_id from persons where parent_first_name = ? and parent_first_name = ?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newRelationship);
			pstmt.setString(2, child.getFirstName());
			pstmt.setString(3, child.getLastName());
			pstmt.setString(4, parent.getParentFirstName());
			pstmt.setString(5, parent.getLastName());
		} catch (SQLException e) {
			log.error("Query failed. Relatinship not updated");
			e.printStackTrace();
		}
	}

	@Override
	public Person getPersonByName(String firstName, String lastName) {
		log.trace("Get Person By Name Called");
		
		Person person = null;
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "select * from persons where first_name = ? and last_name = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			ResultSet rs = pstmt.executeQuery() ;
			
			while(rs.next()) {
				log.info("Person found in DB");
				person = new Person();
				person.setFirstName(rs.getString("first_name"));
				person.setLastName(rs.getString("last_name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return person;
	}

	@Override
	public void deletePerson(Person person) {
		
		Connection conn = ConnectionFactory.getConnection();
		
		String sql = "delete * from persons where first_name = ? and last_name = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, person.getFirstName());
			pstmt.setString(2, person.getLastName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			log.error("Person not deleted. Connection failed!");
			e.printStackTrace();
		}

	}

	@Override
	public List<Person> displayFamilyTree() {
		// TODO Auto-generated method stub
		return null;
	}

}
