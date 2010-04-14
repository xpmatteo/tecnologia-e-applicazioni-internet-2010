package it.xpug.courses;

import it.xpug.html.Element;
import static it.xpug.html.HtmlHelper.*;

import java.util.ArrayList;
import java.util.List;

public class CourseList {
	
	List<Course> courses = new ArrayList();

	public void add(Course course) {
		courses.add(course);
	}

	public int size() {
		return courses.size();
	}

	public Element toHtml() {
		Element table = table().with("id", "courses");
		for (Course course : courses) {
			table.add(course.toTableRow());
		}
		return table;
	}

}
