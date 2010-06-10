package it.xpug.courses.persistence;

import it.xpug.courses.User;
import it.xpug.courses.UserBase;
import it.xpug.courses.persistence.Database;
import it.xpug.courses.persistence.JdbcUserBase;
import it.xpug.courses.util.TestDatabase;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class JdbcUserBaseTest {

	private UserBase userBase;
	private Database database;
	
	@Before
	public void setUp() throws Exception {
		database = new TestDatabase();
		database.execute("delete from users");
		database.execute("insert into users (login, encrypted_password) values ('admin', 'xyz')");

		userBase = new JdbcUserBase(database);
	}
	
	
	@Test
	public void findsAUser() throws Exception {
		User user = userBase.findByLoginAndEncryptedPassword("admin", "xyz");
		assertNotNull(user);
	}
	
	@Test
	public void returnNullForWrongLogin() throws Exception {
		User user = userBase.findByLoginAndEncryptedPassword("piciopacio", "anything");
		assertNull("no user with login piciopacio", user);
	}
	
	@Test
	public void returnNullForWrongPassword() throws Exception {
		User user = userBase.findByLoginAndEncryptedPassword("admin", "wrong password");
		assertNull("wrong password", user);
	}

}
