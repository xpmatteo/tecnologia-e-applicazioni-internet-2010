package it.xpug.courses;

import it.xpug.html.Element;
import static it.xpug.html.HtmlHelper.*;

import java.util.Map;

public class CoursesApplication {

	private final FakeCourseBase courses;
	
	public CoursesApplication(FakeCourseBase courses) {
		this.courses = courses;
	}

	public String doPost(String string, Map<String, String[]> params) {
		String[] title = params.get("title");
		if (null == title) throw new IllegalArgumentException("no title");
		courses.create(title[0]);
		return "/list";
	}

	public Element doGet(String pathInfo) {
		Element table = table().with("id", "courses");
		for (Course course : this.courses.findAll()) {
			table.add(row(cell(course.toString())));
		}
		
		return html(
			head(title("List all courses")),
			body(h1("List all courses")),
			paragraph("These are our courses"),
			table,
			link("new", "New Course")
			);
	}

}
