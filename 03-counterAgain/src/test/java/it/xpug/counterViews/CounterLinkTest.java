package it.xpug.counterViews;

import it.xpug.html.Element;
import it.xpug.html.TextNode;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CounterLinkTest {

	@Test
	public void usesAnchorToDisplayLinks() throws Exception {
		CounterLink link = new CounterLink(123, "inc");
		Element expected = new Element("a").add(new TextNode("inc")).with("href", "?value=123");
		assertEquals(expected, link);
	}
	

}
