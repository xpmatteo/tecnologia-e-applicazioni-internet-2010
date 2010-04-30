package it.xpug.courses;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

import static org.junit.Assert.*;


public class DatabaseTest {

	@Test
	public void connectionWorks() throws Exception {
		Connection connection = getConnection();
		connection.close();
	}

	private Connection getConnection() throws Exception {
        Class.forName("org.hsqldb.jdbcDriver");
        String databaseName = "bin/test";
        return DriverManager.getConnection("jdbc:hsqldb:" + databaseName, "sa", "");
	}
}
