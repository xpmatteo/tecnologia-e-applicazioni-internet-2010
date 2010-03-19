package it.xpug.counter;

import java.io.IOException;
import java.io.Writer;

public class CounterDisplay implements HtmlComponent {

	private final int value;

	public CounterDisplay(int value) {
		this.value = value;
	}

	public void renderOn(Writer writer) throws IOException {
		writer.write("<h1>" + value + "</h1>");
	}

}
