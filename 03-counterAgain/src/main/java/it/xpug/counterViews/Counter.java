package it.xpug.counterViews;

import it.xpug.html.HtmlDocument;
import it.xpug.html.TextNode;
import static it.xpug.html.HtmlHelper.*;

public class Counter {

	private final Integer value;

	public Counter(String value) {
		this.value = Integer.parseInt(value);
	}

	public HtmlDocument toHtmlDocument() {
		HtmlDocument html = html(
			head(title("Counter")),
			body(
				new HexDisplay(value),
				div(
					new CounterLink(value-1, "dec"),
					new TextNode(" "),
					new CounterLink(value+1, "inc")
					)
				)
			);
		return html;
	}

}
