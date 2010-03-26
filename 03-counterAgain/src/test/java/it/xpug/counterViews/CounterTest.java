package it.xpug.counterViews;

import it.xpug.html.Element;

import org.junit.Test;

import static org.junit.Assert.*;


public class CounterTest {

	private Counter counter = new Counter("255");
	private Element document = counter.toHtmlDocument();

	@Test
	public void containsLinks() throws Exception {
		Element inc = document.findLinkByLabel("inc");
		assertEquals("Inc has bad url", "?value=256", inc.getAttribute("href"));
		
		Element dec = document.findLinkByLabel("dec");
		assertEquals("Dec has bad url", "?value=254", dec.getAttribute("href"));
	}
	
	@Test
	public void containsDisplay() throws Exception {
		Counter counter = new Counter("255");
		Element document = counter.toHtmlDocument();
	
		Element display = document.findElementById("display");
		assertEquals("Display shows wrong value", "0xff", display.contentsAsText());
	}
	
	@Test
	public void defaultValueIsZero() throws Exception {
		document = new Counter(null).toHtmlDocument();
		assertEquals("0x0", document.findElementById("display").contentsAsText());
	}

}
