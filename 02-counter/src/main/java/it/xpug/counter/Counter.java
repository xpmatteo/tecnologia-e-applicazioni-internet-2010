package it.xpug.counter;

import java.io.IOException;
import java.io.Writer;

public class Counter implements HtmlComponent {

	private int value;

	public Counter(String valueAsString) {
		if (null == valueAsString) {
			value = 0;
		} else {			
			value = Integer.parseInt(valueAsString);
		}
	}

	public void renderOn(Writer writer) throws IOException {
		VerticalLayout layout = new VerticalLayout();
		layout.add(new CounterDisplay(value));
		layout.add(new CounterLink(value-1, "dec"), new CounterLink(value + 1, "inc"));
		layout.renderOn(writer);
	}

}
