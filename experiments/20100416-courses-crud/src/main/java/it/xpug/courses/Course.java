package it.xpug.courses;

import it.xpug.html.Element;
import static it.xpug.html.HtmlHelper.*;

public class Course {

	private final String title;

	public Course(String title) {
		this.title = title;
	}

	public Element toTableRow() {
		return row(
				cell(title)
				);
	}

}
