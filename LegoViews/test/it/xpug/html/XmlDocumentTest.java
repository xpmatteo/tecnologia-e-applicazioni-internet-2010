package it.xpug.html;

import org.junit.Test;

import static org.junit.Assert.*;



public class XmlDocumentTest {

	@Test
	public void returnsNumberOfMatches() throws Exception {
		XmlDocument xmlDocument = new XmlDocument("<a><b><c>pippo</c></b></a>");
		assertEquals(0, xmlDocument.numberOfMatches("//foo"));
		assertEquals(1, xmlDocument.numberOfMatches("//b"));
		assertEquals("pippo", xmlDocument.nodeText("/a//c"));
	}
	
	@Test
	public void shouldThrowExceptionIfMoreNodesMatch() throws Exception {
        XmlDocument document = new XmlDocument(
        		"<a>" +
        		"  <b>foo</b>" +
        		"  <b>bar</b>" +
        		"</a>");
		assertEquals(2, document.numberOfMatches("//b"));
		try {
			document.nodeText("//b");
			fail("should Throw an Exception If More Nodes Match");
		} catch (ElementNotFoundException ex) {
			assertEquals("\"//b\": expected 1 node, found 2", ex.getMessage());
		}
	}

	@Test
	public void shouldThrowNoNodesMatch() throws Exception {
        XmlDocument document = new XmlDocument("<a></a>");
		assertEquals(0, document.numberOfMatches("//xyzzy"));
		try {
			document.nodeText("//xyzzy");
			fail("should throw");
		} catch (ElementNotFoundException ex) {
			assertEquals("\"//xyzzy\": expected 1 node, found 0", ex.getMessage());
		}
	}
	
}
