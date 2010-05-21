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

	public String pageTitle() {
		return isNew() ? "New Course" : "Edit &ldquo;" + course.getTitle() + "&rdquo;";
	}

	private String action() {
		return isNew() ? "/app/courses/create" : "/app/courses/update";
	}

	private boolean isNew() {
		return course.getId() == null;
	}

	private String label() {
		return isNew() ? "Create" : "Edit";
	}

}
