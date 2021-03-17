package com.revature.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ConnectionFactory {
	Logger log = Logger.getRootLogger();

	public static String URL = "jdbc:postgresql://localhost:5432/postgres?current_schema=codingeval?";

	public static String USERNAME = "postgres";

	public static String PASSWORD = "Pas$wor12020";
	
	public static String NAME = "postgres?current_schema=codingeval";
	
	private static ConnectionFactory connectionFactory = null;

	private ConnectionFactory() {
		
		/*NAME = System.getenv("DB_NAME");
		
		URL = "jdbc:postgresql://" + System.getenv("DB_URL") + ":5432/" + NAME + "?";

		USERNAME = System.getenv("DB_USERNAME");

		PASSWORD = System.getenv("DB_PASSWORD");*/
	}
	
	
	public Connection createConnection() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Failed to load Driver");
		}

		log.info("URL : " + URL);

		try {
			System.out.println();
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("Failed to connect to DB", e);
		}
		return null;
	}
	

	public static synchronized Connection getConnection() {
		
		if (connectionFactory == null) {
			connectionFactory = new ConnectionFactory();
		}

		return connectionFactory.createConnection();

	}

}
