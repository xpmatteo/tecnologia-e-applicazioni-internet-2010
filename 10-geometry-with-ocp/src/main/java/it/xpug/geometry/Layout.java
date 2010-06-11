package it.xpug.geometry;

import it.xpug.html.Element;
import static it.xpug.html.HtmlHelper.*;

public class Layout {

	private static final String APP_NAME = "XP Training Company";
	private final PageComponent component;

	public Layout(PageComponent component) {
		this.component = component;
	}

	public Element toHtml() {
		return html(
			head(
				title(component.pageTitle() + " | " + APP_NAME),
				stylesheetLink("/stylesheets/style.css")),
			body(
				h1(APP_NAME),
				h3(component.pageTitle()),
				component.toHtml())
		);
	}
}
