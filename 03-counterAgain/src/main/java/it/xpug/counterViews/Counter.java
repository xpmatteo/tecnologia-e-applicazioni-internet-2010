package it.xpug.counterViews;

import it.xpug.html.Element;
import it.xpug.html.TextNode;
import static it.xpug.html.HtmlHelper.*;

public class Counter {

	private Integer value;

	public Counter(String value) {
		try {
			this.value = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			this.value = 0;
		}
	}

	public Element toHtmlDocument() {
		Element html = html(
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
