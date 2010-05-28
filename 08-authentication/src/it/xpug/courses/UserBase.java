package it.xpug.courses;

public interface UserBase {

	User findByLoginAndEncryptedPassword(String login, String encryptedPassword);
	
}
