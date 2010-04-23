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
	
	@Test
	public void returnsNumberOfMatches() throws Exception {
		element = div(paragraph("a"), paragraph("b"));
		assertEquals(1, element.numberOfXPathMatches("/div"));
		assertEquals(2, element.numberOfXPathMatches("//p"));
		assertEquals(0, element.numberOfXPathMatches("/zot"));
	}

	@Test
	public void findElementByXPath() throws Exception {
		Element element = div(paragraph("ciao").with("id", "pippo"));
		Element found = element.findByXPath("//p");
		
		assertEquals("p", found.getName());
		assertEquals("pippo", found.getAttribute("id"));
		assertEquals("ciao", found.contentsAsText());
	}
}
