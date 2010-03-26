package it.xpug.counterViews;

import it.xpug.html.Element;
import it.xpug.html.TextNode;

public class HexDisplay extends Element {

	public HexDisplay(int value) {
		super("h1");
		with("id", "display");
		setMyTextContent(convertToHexString(value));
	}

	private Element setMyTextContent(String content) {
		return add(new TextNode(content));
	}

	private String convertToHexString(int value) {
		return "0x" + Integer.toString(value, 16);
	}
}
