package it.xpug.counter;

import java.io.IOException;
import java.io.Writer;

public class CounterLink implements HtmlComponent {

	private final int value;
	private final String label;

	public CounterLink(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public void renderOn(Writer writer) throws IOException {
		String link = String.format("<a href='?value=%d'>%s</a>", value, label);
		writer.write(link);
	}

}
