package it.xpug.geometry;

import it.xpug.html.Element;
import it.xpug.html.HtmlDocument;
import static it.xpug.html.HtmlHelper.*;

public class Layout {

	private static final String APP_NAME = "Geometry!";
	private final PageComponent component;

	public Layout(PageComponent component) {
		this.component = component;
	}

	public Element toHtml() {
		Element contentDiv = div(component.toHtml()).with("id", "content");
		Element navigationDiv = div(navigation()).with("id", "navigation");
		return html(
			head(
				title(component.pageTitle() + " | " + APP_NAME),
				stylesheetLink("/stylesheets/style.css")),
			body(
				h1(APP_NAME),
				h3(component.pageTitle()),
				navigationDiv,
				contentDiv
				)
		);
	}

	private HtmlDocument navigation() {
		return paragraph("Navigation should be here");
	}
}
