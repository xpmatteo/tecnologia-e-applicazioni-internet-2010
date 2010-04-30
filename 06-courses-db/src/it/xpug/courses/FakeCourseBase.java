package it.xpug.courses;

import java.util.ArrayList;
import java.util.List;

public class FakeCourseBase {

	private List<Course> courses = new ArrayList<Course>();

	public long count() {
		return courses.size();
	}

	public void create(Course course) {
		course.setId(courses.size());
		courses.add(course);
	}

	public void update(Course course) {
		Integer id = course.getId();
		courses.set(id, course);
	}

	public Course getLast() {
		return courses.get(courses.size()-1);
	}
	
	public List<Course> findAll() {
		return courses;
	}

	public Course findById(Integer id) {
		return courses.get(id);
	}

	public Course findById(String id) {
		return findById(Integer.parseInt(id));
	}

}
