package it.xpug.courses;

import static it.xpug.html.HtmlHelper.body;
import static it.xpug.html.HtmlHelper.cell;
import static it.xpug.html.HtmlHelper.h1;
import static it.xpug.html.HtmlHelper.head;
import static it.xpug.html.HtmlHelper.html;
import static it.xpug.html.HtmlHelper.link;
import static it.xpug.html.HtmlHelper.paragraph;
import static it.xpug.html.HtmlHelper.row;
import static it.xpug.html.HtmlHelper.table;
import static it.xpug.html.HtmlHelper.title;
import it.xpug.html.Element;

public class CoursesList implements PageComponent {

	private final FakeCourseBase database;

	public CoursesList(FakeCourseBase database) {
		this.database = database;
	}

	public Element toHtml() {		
		return html(
			head(title("List all courses")),
			body(
				h1("List all courses")),
				paragraph("These are our courses"),
				coursesTable(),
				link("/app/courses/new", "New Course")
			);
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
