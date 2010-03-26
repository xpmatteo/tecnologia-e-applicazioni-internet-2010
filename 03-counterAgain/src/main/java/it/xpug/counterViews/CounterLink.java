package it.xpug.counterViews;

import it.xpug.html.Element;
import it.xpug.html.TextNode;

public class CounterLink extends Element {

	public CounterLink(int value, String label) {
		super("a");
		with("href", "?value=" + value);
		add(new TextNode(label));
	}

}
