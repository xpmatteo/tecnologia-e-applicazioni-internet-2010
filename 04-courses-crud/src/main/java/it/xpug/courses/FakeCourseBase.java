package it.xpug.courses;

import java.util.ArrayList;
import java.util.List;

public class FakeCourseBase {

	private List<Course> courses = new ArrayList<Course>();

	public long count() {
		return courses.size();
	}

	public void create(Course course) {
		courses.add(course);
	}

	public Course getLast() {
		return courses.get(courses.size()-1);
	}
	
	public List<Course> findAll() {
		return courses;
	}

}
