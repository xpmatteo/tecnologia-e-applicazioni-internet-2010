package it.xpug.html;

import org.junit.Test;

import static org.junit.Assert.*;
import static it.xpug.html.HtmlHelper.*;

public class HtmlDocumentXPathTest {
	private Element element = html(head(title("title")));

	@Test
	public void matchesXPathExpressions() throws Exception {
		assertTrue("no //title", element.matchesXPath("//title"));
		assertFalse("no //zot", element.matchesXPath("//zot"));
	}
	
	@Test
	public void getsTextContentByXPath() throws Exception {
		assertEquals("title", element.textContentByXPath("//title"));
	}
	
	@Test(expected=ElementNotFoundException.class)
	public void throwsForNonexistentElement() throws Exception {
		element.textContentByXPath("//blabla");
	}
	
}
