package it.xpug.counterViews;

import it.xpug.html.Element;
import it.xpug.html.TextNode;

import org.junit.Test;

import static org.junit.Assert.*;


public class HexDisplayTest {

	@Test
	public void formatsNumberInHex() throws Exception {
		HexDisplay display = new HexDisplay(255);
		assertEquals("0xff", display.contentsAsText());
	}

	@Test
	public void usesH1ToDisplayABigNumber() throws Exception {
		HexDisplay display = new HexDisplay(3);
		assertEquals(new Element("h1").with("id", "display").add(new TextNode("0x3")), display);
	}
	
	@Test
	public void formatsNegativeNumbers() throws Exception {
		HexDisplay display = new HexDisplay(-1);
		assertEquals("-0x1", display.contentsAsText());
	}
}
