package it.xpug.counterViews;

import it.xpug.html.Element;
import it.xpug.html.HtmlDocument;
import it.xpug.html.TextNode;

import org.junit.Test;

import static org.junit.Assert.*;


public class CounterTest {

	@Test
	public void usesH1ToDisplayABigNumber() throws Exception {
		HexDisplay display = new HexDisplay(3);
		assertEquals(new Element("h1").add(new TextNode("0x3")), display);
	}
	
	@Test
	public void usesAnchorToDisplayLinks() throws Exception {
		CounterLink link = new CounterLink(123, "inc");
		Element expected = new Element("a").add(new TextNode("inc")).with("href", "?value=123");
		assertEquals(expected, link);
	}
	
	@Test
	public void counterReturnsAnHtmlDocument() throws Exception {
		Counter counter = new Counter("255");
		HtmlDocument document = counter.toHtmlDocument();
	
		Element inc = document.findLinkByLabel("inc");
		assertEquals("Inc c'è ma non ha la url giusta", "?value=256", inc.getAttribute("href"));
		
		Element dec = document.findLinkByLabel("dec");
		assertEquals("Dec c'è ma non ha la url giusta", "?value=254", dec.getAttribute("href"));
	}
}
