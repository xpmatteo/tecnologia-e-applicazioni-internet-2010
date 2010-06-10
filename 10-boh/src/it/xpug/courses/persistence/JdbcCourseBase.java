package it.xpug.courses.persistence;

import it.xpug.courses.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class JdbcCourseBase implements CourseBase {

	private final Database database;

	public JdbcCourseBase(Database database) {
		this.database = database;
	}

	public long count() {
		ListOfRows rows = database.select("select count(*) as courses_count from courses");
		Map<String, Object> row = rows.get(0);
		return (Integer) row.get("courses_count");
	}

	public void create(Course course) {
		database.execute("insert into courses (name) values(?)", course.getTitle());
	}

	public void deleteAll() {
		database.execute("delete from courses");
	}

	public List<Course> findAll() {
		List<Course> result = new ArrayList<Course>();
		ListOfRows rows = database.select("select * from courses");
		for (int i=0; i<rows.size(); i++) {
			Map<String, Object> row = rows.get(i);
			Course course = new Course((String) row.get("name"));
			result.add(course);
		}
		return result;
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

