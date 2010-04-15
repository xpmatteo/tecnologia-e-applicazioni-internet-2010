package it.xpug.html;

import static org.junit.Assert.assertTrue;

public class Assert {

	public static void assertMatches(String xpath, Element html) {
		assertTrue(xpath, html.matchesXPath(xpath));
	}

}
