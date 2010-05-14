package it.xpug.courses;

import java.util.List;

public class JdbcCourseBase implements CourseBase {

	public long count() {
		throw new RuntimeException("Not yet implemented!");
	}

	public void create(Course course) {
		throw new RuntimeException("Not yet implemented!");
	}

	public List<Course> findAll() {
		throw new RuntimeException("Not yet implemented!");
	}

	public Course findById(Integer id) {
		throw new RuntimeException("Not yet implemented!");
	}

	public Course findById(String id) {
		throw new RuntimeException("Not yet implemented!");
	}

	public Course getLast() {
		throw new RuntimeException("Not yet implemented!");
	}

	public void update(Course course) {
		throw new RuntimeException("Not yet implemented!");
	}

}
