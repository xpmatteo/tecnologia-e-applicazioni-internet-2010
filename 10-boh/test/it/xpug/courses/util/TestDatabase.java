package it.xpug.courses.util;

import java.sql.Connection;
import java.sql.DriverManager;

import it.xpug.courses.persistence.Database;

public class TestDatabase extends Database {

	private static String url = "jdbc:hsqldb:file:./db/databases/courses_test;shutdown=true";
	private static String username = "sa";
	private static String password = "";

	public TestDatabase() {
		super(getConnection());
	}

	private static Connection getConnection() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
