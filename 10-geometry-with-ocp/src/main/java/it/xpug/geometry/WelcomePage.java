package it.xpug.geometry;

import it.xpug.html.Element;
import static it.xpug.html.HtmlHelper.*;

import javax.servlet.http.HttpServletRequest;

public class WelcomePage implements PageComponent {

	public String pageTitle() {
		return "Welcome";
	}

	public Element toHtml() {
		return paragraph("Welcome to our Geometry App!");
	}

	public boolean wantsToHandle(HttpServletRequest request) {
		throw new RuntimeException("Not yet implemented!");
	}
}
