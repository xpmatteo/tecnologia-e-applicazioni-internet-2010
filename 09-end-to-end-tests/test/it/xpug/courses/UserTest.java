package it.xpug.courses;

import org.junit.Test;

import static org.junit.Assert.*;


public class UserTest {
	
	private static final String PASSWORD = "pippo";
	private static final String SHA1_OF_PASSWORD = "d012f68144ed0f121d3cc330a17eec528c2e7d59";

	@Test
	public void willEncryptPassword() throws Exception {
		assertEquals(SHA1_OF_PASSWORD, User.encrypt(PASSWORD));
	}
	
}
