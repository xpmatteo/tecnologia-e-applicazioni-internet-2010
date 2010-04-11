package it.xpug.html;

import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;

import static org.junit.Assert.*;


public class HtmlDocumentTest {

	@Test
	public void textNodeToHtml() throws Exception {
		HtmlDocument text = new TextNode("ciao");
		assertRenders("ciao", text);
	}
	
	@Test
	public void elementToHtml() throws Exception {
		Element paragraph = new Element("p");
		paragraph.add(new TextNode("ciao "))
			.add(new Element("em").add(new TextNode("ciao")));
		assertRenders("<p>ciao <em>ciao</em></p>", paragraph);
	}
	
	@Test
	public void attributes() throws Exception {
		Element form = new Element("form");
		form.with("method", "get").with("action", ".");
		assertRenders("<form action='.' method='get'></form>", form);
	}

	@Test
	public void someEmptyElementsAreRenderedWithASingleTag() throws Exception {
		assertRenders("<p></p>", new Element("p"));
		assertRenders("<br />", new Element("br", Element.EmptyMode.SINGLE_TAG));
		
		Element withAttribute = new Element("input", Element.EmptyMode.SINGLE_TAG).with("type", "text");
		assertRenders("<input type='text' />", withAttribute);
		
		Element nonEmpty = new Element("x", Element.EmptyMode.SINGLE_TAG).add(new TextNode("foo"));
		assertRenders("<x>foo</x>", nonEmpty);
	}
	
	@Test
	public void differentVariantsAreUnequal() throws Exception {
		assertNotEquals(new TextNode("x"), new Element("x"));
	}
	
	@Test
	public void textNodeEquality() throws Exception {
		TextNode node = new TextNode("x");
		assertNotEquals(node, null);
		assertNotEquals(node, "instance of other class");
		assertEquals(node, node);
		assertEquals(node, new TextNode("x"));
		assertNotEquals(node, new TextNode("different content"));
	}
	
	@Test
	public void elementEquality() throws Exception {
		Element element = new Element("x").with("a", "v").add(new TextNode("a"));
		Element clone = new Element("x").with("a", "v").add(new TextNode("a"));
		Element differentName = new Element("y").with("a", "v").add(new TextNode("a"));
		Element differentAttribute = new Element("y").with("a", "something else").add(new TextNode("a"));
		Element differentContent = new Element("x").with("a", "v").add(new TextNode("bla"));
		assertNotEquals(element, null);
		assertNotEquals(element, "a string");
		assertEquals(element, element);
		assertEquals(element, clone);
		assertNotEquals(element, differentName);
		assertNotEquals(element, differentAttribute);
		assertNotEquals(element, differentContent);
	}
	
	private void assertNotEquals(Object a, Object b) {
		assertFalse(a.equals(b));
	}

	private void assertRenders(String expected, HtmlDocument html) throws IOException {
		StringWriter writer = new StringWriter();
		html.renderOn(writer);
		assertEquals(expected, writer.toString());
	}
	
	@Test
	public void returnsATextRepresentationOfContents() throws Exception {
		HtmlDocument textNode = new TextNode("pippo");
		assertEquals("pippo", textNode.contentsAsText());
	}
	
	@Test
	public void returnsATextRepresentationOfInternalContents() throws Exception {		
		Element p = new Element("p").add(new TextNode("bar"));
		assertEquals("bar", p.contentsAsText());
	}
	
	@Test
	public void contentsOfEmptyTag() throws Exception {
		Element empty = new Element("p");
		assertEquals("", empty.contentsAsText());
	}
	
	@Test
	public void contentsOfElementWithMoreChildren() throws Exception {
		Element twoChildren = new Element("p").add(new TextNode("a")).add(new TextNode("b"));
		assertEquals("ab", twoChildren.contentsAsText());
	}
	
	@Test
	public void contentsOfNestedElements() throws Exception {
		Element div = new Element("div").add(new Element("p").add(new TextNode("hello")));
		assertEquals("hello", div.contentsAsText());
	}

}
