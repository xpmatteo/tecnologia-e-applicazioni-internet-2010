package it.xpug.counterViews;

import it.xpug.html.Element;
import it.xpug.html.TextNode;

public class HexDisplay extends Element {

	private final Integer value;

	public HexDisplay(int value) {
		super("h1");
		this.value = value;
		setMyTextContent(valueToDisplay());
	}

	private Element setMyTextContent(String content) {
		return add(new TextNode(content));
	}

	private String valueToDisplay() {
		return "0x" + Integer.toString(value, 16);
	}
}
