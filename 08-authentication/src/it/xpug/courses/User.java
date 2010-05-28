package it.xpug.courses;

import java.security.MessageDigest;

public class User {

	public User(Integer userId, String login) {
	}

	public static String encrypt(String clearText) {
		try {
			MessageDigest encrypter = MessageDigest.getInstance("SHA-1");
			byte[] digest = encrypter.digest(clearText.getBytes("UTF-8"));
			return convertToHexString(digest);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String convertToHexString(byte[] digest) {
		String result = "";
		for (int i = 0; i < digest.length; i++) {
			byte aByte = digest[i];
			result += digitToHex((aByte & 0xF0) >> 4);
			result += digitToHex(aByte & 0x0F);
		}
		return result;
	}

	private static String digitToHex(Integer value) {
		String[] symbols = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
		return symbols[value];
	}
}
