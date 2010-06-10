package it.xpug.courses.persistence;

import it.xpug.courses.User;
import it.xpug.courses.UserBase;

public class FakeUserBase implements UserBase {

	private String login;
	private String encryptedPassword;
	private int userId;

	public void containsOnlyThisUser(int userId, String login, String password) {
		this.userId = userId;
		this.login = login;
		this.encryptedPassword = User.encrypt(password);
	}

	public User findByLoginAndEncryptedPassword(String login, String encryptedPassword) {
		if (login.equals(this.login) && encryptedPassword.equals(this.encryptedPassword)) {
			return new User(userId, "admin"); 
		}
		return null;
	}

}
