package it.xpug.courses;

import it.xpug.html.Element;
import static it.xpug.html.HtmlHelper.*;

public class CoursesList implements PageComponent {

	private final CourseBase database;

	public CoursesList(CourseBase database) {
		this.database = database;
	}

	public Element toHtml() {		
		return div(
			coursesTable(),
			link("/app/courses/new", "New Course")
		);
	}

	public String pageTitle() {
		return "All Courses";
	}

	private Element coursesTable() {
		Element table = table().with("id", "courses");
		for (Course course : this.database.findAll()) {
			table.add(courseRow(course));
		}
		return table;
	}

	private Element courseRow(Course course) {
		return row(
				cell(course.getTitle()),
				cell(link("/app/courses/edit?id=" + course.getId(), "edit"))
				);
	}

}
