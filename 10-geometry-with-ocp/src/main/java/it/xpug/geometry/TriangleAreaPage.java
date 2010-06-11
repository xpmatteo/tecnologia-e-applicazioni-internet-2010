package it.xpug.geometry;

import javax.servlet.http.HttpServletRequest;

import it.xpug.html.Element;
import static it.xpug.html.HtmlHelper.*;


public class TriangleAreaPage implements PageComponent {

	public String pageTitle() {
		return "Area of a triangle";
	}

	public Element toHtml() {
		return paragraph("Area of a triangle");
	}

	public boolean wantsToHandle(HttpServletRequest request) {
		throw new RuntimeException("Not yet implemented!");
	}

}
