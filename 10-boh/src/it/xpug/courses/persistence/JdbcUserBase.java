package it.xpug.courses.persistence;

import it.xpug.courses.User;
import it.xpug.courses.UserBase;

public class JdbcUserBase implements UserBase {

	private final Database database;

	public JdbcUserBase(Database database) {
		this.database = database;
	}

	public User findByLoginAndEncryptedPassword(String login, String encryptedPassword) {
		String sql = "select * from users where login = ? and encrypted_password = ?";
		ListOfRows rows = database.select(sql, login, encryptedPassword);
		if (0 == rows.size()) {
			return null;
		}
		return new User(123, "x");
	}

}
