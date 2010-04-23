package it.xpug.courses;

import it.xpug.html.Element;
import static it.xpug.html.HtmlHelper.*;

public class CourseForm implements PageComponent {

	private final Course course;

	public CourseForm(Course course) {
		this.course = course;
	}

	public Element toHtml() {
		return form(action(), "post",
				paragraph(text("Title:"), textField("title", course.getTitle())),
				hiddenField("id", "" + course.getId()),
				paragraph(submitButton(label()))
				);
	}

	private String action() {
		return course.getId() == null ? "/app/courses/create" : "/app/courses/update";
	}

	private String label() {
		return course.getId() == null ? "Create" : "Edit";
	}

}
