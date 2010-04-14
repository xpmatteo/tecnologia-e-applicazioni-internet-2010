package it.xpug.courses;

import it.xpug.html.Element;
import static it.xpug.html.HtmlHelper.*;

public class CourseListPage {

	private final CourseList list;

	public CourseListPage(CourseList list) {
		this.list = list;
	}

	public Element toHtml() {
		return html(
				head(title("Courses list")),
				body(
					h1("Courses"),
					list.toHtml()
				));
	}
}
