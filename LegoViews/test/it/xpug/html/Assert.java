package it.xpug.html;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static java.lang.String.format;

public class Assert {

	public static void assertMatches(String xpath, Element html) {
		assertMatches(null, xpath, html);
	}

	public static void assertMatches(String message, String xpath, Element html) {
		assertTrue(message, html.matchesXPath(xpath));
	}
	
	public static void assertContainsTextField(String name, String value, Element html) throws ElementNotFoundException {
		assertContainsInput("text", name, value, html);
	}

	public static void assertContainsHiddenField(String name, String value, Element html) throws ElementNotFoundException {
		assertContainsInput("hidden", name, value, html);
	}

	public static void assertContainsPasswordField(String name, String value, Element html) throws ElementNotFoundException {
		assertContainsInput("password", name, value, html);
	}

	public static void assertContainsSubmitButton(String label, Element html) throws ElementNotFoundException {
		assertContainsInput("password", label, "", html);
	}

	public static void assertContainsInput(String type, String name, String value, Element html) throws ElementNotFoundException {
		assertMatches("no form found", "//form", html);
		
		assertMatches(
				format("no %s input element found", type), 
				format("//form//input[@type='%s']", type), html);
		
		String xpath = format("//form//input[@type='%s'][@name='%s']", type, name);
		assertMatches(
				format("no input element found with name '%s'", name), 
				xpath, html);
		
		String actual = html.findByXPath(xpath).getAttribute("value");
		assertEquals(
				format("value of input element '%s'", name), 
				value, actual);
	}

}
